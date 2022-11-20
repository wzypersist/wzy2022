package com.wzy.rpcv2.consumer;

import com.wzy.rpcv2.protocol.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RpcNetTransport {
    
    private String host;
    private int port;

    public RpcNetTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }
    
    public Object send(RpcRequest request){
        Socket socket = null;
        Object result = null;
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            socket = new Socket(host,port);
            
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(request);
            outputStream.flush();
            
            inputStream = new ObjectInputStream(socket.getInputStream());
            result = inputStream.readObject();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    
}
