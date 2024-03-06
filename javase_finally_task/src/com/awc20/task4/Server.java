package com.awc20.task4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        /*① 先读取本地文件中所有的正确用户信息。
        ② 当有客户端来链接的时候，就开启一条线程。
        ③ 在线程里面判断当前用户是登录操作还是注册操作。
        ④ 登录，校验用户名和密码是否正确
        ⑤ 注册，校验用户名是否唯一，校验用户名和密码的格式是否正确
        ⑥ 如果登录成功，开始聊天
        ⑦ 如果注册成功，将用户信息写到本地，开始聊天*/

        //读取本地文件中用户信息
        //建立链接
        ServerSocket ss = new ServerSocket(10000);
        Socket socket = ss.accept();
        System.out.println("连接建立成功");
        //接收信息
        new Thread(new MyRunnable(socket)).start();

    }
}



class MyRunnable implements  Runnable{
    Socket socket;
    public MyRunnable(Socket socket) {
        this.socket=socket;
    }

    @Override
    public void run() {
        //循环接收信息
        while (true) {
            try {
                //接收信息
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String s = br.readLine();
                System.out.println(s);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
