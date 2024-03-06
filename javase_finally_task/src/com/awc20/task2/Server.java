package com.awc20.task2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class Server {
    public static void main(String[] args) throws IOException {
        //创建线程池
        ThreadPoolExecutor pool=new ThreadPoolExecutor(
                4,
                6,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        //创建对象serverSocket,建立连接
        ServerSocket ss = new ServerSocket(10000);
        while (true) {
            //接收客户端传来的数据并存入本地文件夹
            Socket socket = ss.accept();
            pool.submit(new MyRunnable(socket));
        }


    }
}
