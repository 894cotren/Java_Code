package com.awc20.task6;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket ss =new ServerSocket(10000);
        while (true){
            Socket socket = ss.accept();
            new Thread(new MyRunnable(socket)).start();
        }
    }
}

class MyRunnable implements Runnable{
    static ArrayList<Socket> list =new ArrayList<>();
    Socket socket;

    public MyRunnable(Socket socket) {
        this.socket = socket;
        list.add(socket);
    }

    @Override
    public void run() {
        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(true){
                String s = br.readLine();
                System.out.println(s);
                //回写数据,给所有人
                returnWrite(Thread.currentThread().getName()+s);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void returnWrite(String t) throws IOException {
        for (Socket socket : list) {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bw.write(t);
            bw.newLine();
            bw.flush();
        }
    }
}