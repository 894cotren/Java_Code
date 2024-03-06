package com.awc20.task2;

/*
* 客户端,功能是读取本地文件上传到服务器
* */

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        //创建socket对象,连接上服务器IP
        Socket socket = new Socket("127.0.0.1", 10000);
        //读入图片数据并上传到服务器
        //用字节缓冲流+字节数组读取,这样快些
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("finally_task\\clientlib\\roxy.jpg"));
        OutputStream os = socket.getOutputStream();
        byte[] bytes = new byte[1024];
        int len;
        while ((len=bis.read(bytes))!=-1){
            //写入数据到服务器
            os.write(bytes,0,len);
        }

        //关闭本地流,发送结束标记给服务器
        bis.close();
        socket.shutdownOutput();
        //等待接收服务器反馈消息
        //因为就接收一条数据,所以转成字符流并用高级流字符缓冲流的方法

        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String s = br.readLine();
        System.out.println(s);

        //关闭连接.
        socket.close();
    }
}
