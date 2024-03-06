package com.awc20.task1;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

public class MyRunnable implements Runnable{
    Socket socket;

    public MyRunnable(Socket socket) {
        this.socket=socket;
    }

    @Override
    public void run() {
        //创建随机命名UUID
        try {
            String pictureName = UUID.randomUUID().toString().replace("-", "");
            BufferedInputStream bis=new BufferedInputStream(socket.getInputStream());
            BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream("finally_task\\serverlib\\"+pictureName+".jpg"));
            byte[] bytes = new byte[1024];
            int len;
            while((len=bis.read(bytes))!=-1){
                bos.write(bytes,0,len);
            }

            //发送接收反馈到客服端
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bw.write("上传成功");
            bw.newLine();
            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭连接
            if(socket!=null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
