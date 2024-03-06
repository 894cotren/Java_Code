package com.awc20.task5;

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
        //开启一条多线程用于接收服务器回写
        new Thread(new ClientRunnbale(socket)).start();

        //循环输入
        while (true) {
            System.out.println("请输入:");
            String s = sc.nextLine();
            bw.write(s);
            bw.newLine();
            bw.flush();
        }
    }
}
