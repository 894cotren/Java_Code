package com.awc20.task3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        //创建serversocket对象,建立连接
        ServerSocket ss = new ServerSocket(10001);

        while(true){
            //接收信息
            Socket socket = ss.accept();
            new Thread(new MyRunnable(socket)).start();

        }


        //关闭连接,释放资源
        //服务器需要关闭连接吗?
        //好像不需要

    }
}
