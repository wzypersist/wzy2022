package com.wzy.rpcv2.provider;

import com.wzy.rpcv2.protocol.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ProcessorHandler implements Runnable {
    
    private Socket socket;
    private Map<String,Object> handlerMap;

    public ProcessorHandler(Socket socket, Map<String, Object> handlerMap) {
        this.socket = socket;
        this.handlerMap = handlerMap;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            RpcRequest rpcRequest = (RpcRequest) inputStream.readObject();
            Object res = invoke(rpcRequest);

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(res);
            outputStream.flush();

        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private Object invoke(RpcRequest request) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object service = handlerMap.get(request.getClassName());
        if(service == null){
            throw new RuntimeException("server not found:" + service);
        }
        Object[] parameters = request.getParameters();
        Class<?>[] types = new Class[parameters.length]; 
        for (int i = 0; i < parameters.length; i++) {
            types[i] = parameters[i].getClass();
        }
        String methodName = request.getMethodName();
        Class<?> clazz = Class.forName(request.getClassName());
        Method method = clazz.getMethod(methodName, types);
        Object result = method.invoke(service, parameters);
        return result;
    }
}
