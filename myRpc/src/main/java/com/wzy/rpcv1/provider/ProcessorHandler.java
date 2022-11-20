package com.wzy.rpcv1.provider;

import com.wzy.rpcv1.protocol.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

public class ProcessorHandler implements Runnable {
    
    private Socket socket;
    private Object service;

    public ProcessorHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        try {
            // Object~Stream也是包装类，其作用在于将字节流解析成java对象
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            // 解析出具体的请求信息（RPCRequest）
            RpcRequest rpcRequest = (RpcRequest)objectInputStream.readObject();
            // 反射调用本地服务
            Object res = invoke(rpcRequest);
            // 返回执行结果
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(res);
            objectOutputStream.flush();
        }catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        
    }

    public Object invoke(RpcRequest request)  throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException{
        Object[] parameters = request.getParameters();
        Class[] types = new Class[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            types[i] = parameters[i].getClass();
        }
        Class<?> clazz = Class.forName(request.getClassName());
        Method method = clazz.getMethod(request.getMethodName(), types);
        Object res = method.invoke(service, parameters);
        return res;
    }
}
