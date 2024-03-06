package com.awc20.task4;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Scanner sc= new Scanner(System.in);
        //连接服务器
        Socket socket = new Socket("127.0.0.1", 10000);
        System.out.println("服务器连接成功");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        //菜单提示登录or注册
        while (true) {
            System.out.println("==========欢迎来到聊天室==========");
            System.out.println("===1.登录================2.注册===");
            System.out.println("请输入你的选择:");
            String choose = sc.nextLine();
            switch (choose){
                case "1":
                    System.out.println("开始登录");
                    //开始登录向服务器发送1
                    tell2Server(bw,"login");
                    break;
                case "2":
                    System.out.println("开始注册");
                    //开始注册向服务器发送2
                    tell2Server(bw,"register");
                    break;
                default:
                    System.out.println("输入有误,请重新输入");
            }
        }


        //把登录或者注册逻辑发给服务器
        //等待服务器回写登录成功  这里有分支
        //登录成功后开始聊天
    }


    public static void tell2Server(BufferedWriter bw,String s) throws IOException {
        bw.write(s);
        //bw.flush();
        bw.close();
    }

}


