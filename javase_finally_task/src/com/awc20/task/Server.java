package com.awc20.task7;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;

public class Server {

    //存储客户端流集合
    static ArrayList<Socket> list =new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(10000);
        //读取本地用户data
        Properties prop=new Properties();
        FileInputStream fis = new FileInputStream("finally_task\\serverlib\\data.txt");
        prop.load(fis);
        fis.close();


        //来一个客户端,创建一个线程去连接
        while (true) {
            Socket socket = ss.accept();
            System.out.println("有客户端连接");
            new Thread(new MyRunnable(socket,prop)).start();
        }
    }
}


class MyRunnable implements Runnable{
    Socket socket;
    Properties prop;

    public MyRunnable(Socket socket, Properties prop) {
        this.socket = socket;
        this.prop = prop;
    }

    @Override
    public void run() {
        //读取客户端发送的消息
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                //第一次读的是执行什么操作
                String choose = br.readLine();
                switch (choose){
                    case "login" -> login(br);
                    case "register" -> System.out.println("用户选择了注册操作");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //获取用户登陆时传递过来的信息并判断
    private void login(BufferedReader br) throws IOException {
        System.out.println("用户选择了登录操作");
        //第二次读取客户端发过来的用户信息
        String userinfo = br.readLine();
        System.out.println(userinfo);
        //接收到了用户信息,执行判断.
        //username=zhangsan&password=123
        String[] split = userinfo.split("&");
        String usernameInput = split[0].split("=")[1];
        String passwordInput = split[1].split("=")[1];
        System.out.println(usernameInput);
        System.out.println(passwordInput);
        //判断用户信息
        if(prop.containsKey(usernameInput)){
            //用户名存在
            //判断密码
            String rightPassword = prop.get(usernameInput)+"";
            if(rightPassword.equals(passwordInput)){
                //密码正确
                writeMessage2Client("1");
                //把该客户端流对象加入集合当中
                Server.list.add(socket);
                //此循环聊天
                talk2All(br,usernameInput);
            }else{
                //密码错误
                writeMessage2Client("2");
            }
        }else{
            //用户名不存在
            writeMessage2Client("3");
        }
    }

    private void talk2All(BufferedReader br,String username) throws IOException {
        //循环接收客户端消息并打印到控制台
        while (true) {
            String messageFromClient = br.readLine();
            System.out.println(username+"发来消息:"+messageFromClient);

            //收到消息并转发给所有客户端
            for (Socket s : Server.list) {
                writeMessage2Client(s,username+"发来消息:"+messageFromClient);
            }
        }
    }

    //服务器回写客服端方法
    public void writeMessage2Client(String message) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bw.write(message);
        bw.newLine();
        bw.flush();
    }

    public void writeMessage2Client(Socket s,String message) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        bw.write(message);
        bw.newLine();
        bw.flush();
    }
}