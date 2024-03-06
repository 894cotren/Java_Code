package com.awc20.task3;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        //连接服务器
        Socket socket=new Socket("127.0.0.1",10001);
        //循环提示发送消息到服务器
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        Scanner sc=new Scanner(System.in);
        while (true){
            //如果发送关键词"exit chat"退出聊天室
            System.out.print("请输入>:");
            String s = sc.nextLine();
            if("exit chat".equals(s)){
                break;
            }
            bw.write(s);
            bw.flush();
            //服务器接收回写
            /*InputStreamReader isr=new InputStreamReader(socket.getInputStream());
            char[] chars = new char[1024];
            isr.read(chars);
            System.out.println(chars);*/
        }
        //这里需要结束标记吗
        //后面直接释放资源了好像不需要
        socket.shutdownOutput();
        //断开连接,释放资源
        socket.close();
    }
}
