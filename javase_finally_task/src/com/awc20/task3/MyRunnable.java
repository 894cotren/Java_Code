package com.awc20.task3;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class MyRunnable implements Runnable{

    public static ArrayList<Socket> list;
    Socket socket;
    public MyRunnable(Socket socket) {
        this.socket=socket;
        /*list.add(socket);*/
    }

    @Override
    public void run() {
        try {

            //获取输入流
            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            char[] chars = new char[1024];
            int len;
            while ((len=isr.read(chars))!=-1){
                //System.out.println(Thread.currentThread().getName());
                //先打印到服务端控制台,再转发给所有客户端
                System.out.println(chars);
                //回写给所有客户端
                /*returnWrite(chars,len);*/
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //回写方法
    public void returnWrite(char[] chars,int len) throws IOException {
        for (Socket socket : list) {
            OutputStreamWriter osw=new OutputStreamWriter(socket.getOutputStream());
            osw.write(chars,0,len);
        }
    }
}
