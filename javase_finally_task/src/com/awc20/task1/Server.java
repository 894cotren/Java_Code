package com.awc20.task1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        //创建对象serverSocket,建立连接
        ServerSocket ss = new ServerSocket(10000);
        while (true) {
            //接收客户端传来的数据并存入本地文件夹
            Socket socket = ss.accept();
            new Thread(new MyRunnable(socket)).start();
        }


    }
}
