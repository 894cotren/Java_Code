package com.awc20.task7;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        //连接服务器
        Socket socket = new Socket("127.0.0.1", 10000);
        System.out.println("服务器已经连接成功");
        //创建流
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        //搭建菜单框架
        while (true) {
            System.out.println("=======聊天室=======");
            System.out.println("1.登录");
            System.out.println("2.注册");
            System.out.println("请输入你的选择:");
            Scanner sc=new Scanner(System.in);
            String choose = sc.nextLine();
            switch (choose){
                case "1" -> login(socket);
                case "2" -> System.out.println("注册账户");
                default -> System.out.println("没有这个选项,请重新输入");
            }
        }
    }


    //登录方法
    private static void login(Socket socket) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        //键盘录入信息
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入用户名");
        String username = sc.nextLine();
        System.out.println("请输入密码");
        String password = sc.nextLine();

        //拼接信息
        //username=zhangsan&password=123
        StringBuilder sb=new StringBuilder();
        sb.append("username=").append(username).append("&password=").append(password);

        //第一次发送的是执行什么操作
        bw.write("login");
        bw.newLine();
        bw.flush();

        //第二次发送的是用户信息
        //把信息输出给服务器
        bw.write(sb.toString());
        bw.newLine();
        bw.flush();

        //接收服务器回写消息
        //需要获取输入流
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String messageFromServer = br.readLine();
        //对回写的消息进行判断
        //1 登录成功,进入聊天室 2密码错误,请重新输入 3用户名不存在,请先注册
        if(messageFromServer.equals("1")){
            System.out.println("登录成功,进入聊天室");
            //登录成功,开启一条线程用于专门接收聊天室所有人消息
            new Thread(new ClientMyRunnable(socket)).start();
            //开启聊天
            talk2All(bw);
        }else if(messageFromServer.equals("2")){
            System.out.println("密码错误,请重新输入");
        }else if(messageFromServer.equals("3")){
            System.out.println("用户名不存在,请先注册");
        }

    }

    private static void talk2All(BufferedWriter bw) throws IOException {
        Scanner sc=new Scanner(System.in);
        while (true){
            //键盘录入并发送服务器
            System.out.println("请输入聊天内容:");
            String s = sc.nextLine();
            bw.write(s);
            bw.newLine();
            bw.flush();
        }
    }
}


class ClientMyRunnable implements Runnable{
    Socket socket;

    public ClientMyRunnable(Socket socket) {
        this.socket=socket;
    }

    @Override
    public void run() {
        //接收服务器发过来的信息
        while (true) {
            try {
                BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String msg = br.readLine();
                System.out.println(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
