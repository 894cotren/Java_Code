package com.awc20.task6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientRunnbale implements Runnable {
    Socket socket;
    public ClientRunnbale(Socket socket) {
        this.socket=socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //循环接收服务器消息
            while(true){
                String s = br.readLine();
                System.out.println(s);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
