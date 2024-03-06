package com.awc20.task6;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        //连接服务器
        Socket socket = new Socket("127.0.0.1",10000);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        Scanner sc=new Scanner(System.in);
        while (true) {
            System.out.println("=====欢迎来到聊天室=====");
            System.out.println("1.登录====2.注册=======");
            System.out.println("请输入:");
            String choose = sc.nextLine();
            switch (choose){
                case "1":
                    System.out.println("开始登录");
                    loginServer(bw);
                    break;
                case "2":
                    System.out.println("开始注册");
                    break;
                default:
                    System.out.println("输入有误,请重新输入");
            }
        }


        //以下是执行开始聊天后的过程
        //开启一条多线程用于接收服务器回写
        /*new Thread(new ClientRunnbale(socket)).start();
        //循环输入
        while (true) {
            System.out.println("请输入:");
            String s = sc.nextLine();
            bw.write(s);
            bw.newLine();
            bw.flush();*/
        //}
    }

    //登录方法
    private static void loginServer(BufferedWriter bw) {
        //首先发送登录关键词给服务器提示登录
        tell2Server(bw,"login");


        //接收账户名,并提交给服务器
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入账户名");
        String name = sc.nextLine();
        System.out.println("请输入密码");
        String passWord = sc.nextLine();
        StringBuilder sb=new StringBuilder();
        //username=zhangsan&password=123
        sb.append("username=").append(name+"&").append("password=").append(passWord);

    }

    private static void tell2Server(BufferedWriter bw,String s){
        try {
            bw.write(s);
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
