package com.alibaba.interview;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * @author: WSC
 * @Create 2020/9/2 14:49
 */
public class TcpServer {

    public static void main(String[] args) throws Exception {
        //创建一个ServerSocket监听一个端：8888
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("TCP服务器已经启动，端口是8888");
        //无限循环
        while (true) {
            //等待客户端的请求，如果有请求分配一个Socket
            Socket socket = serverSocket.accept();
            //根据标准输入构造一个BufferedReader对象
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String buffer = null;
            //循环读取输入的每一行数据
            while ((buffer = reader.readLine()) != null && !buffer.equals("")) {
                System.out.println(Arrays.toString(buffer.split(" ")));
                String replace = buffer.replace(" ", "");
                System.out.println(replace);
                System.out.println(replace.substring(0, 4));
                System.out.println(replace.substring(4, 6));
                System.out.println(replace.substring(6, 8));
                System.out.println(replace.substring(8, 12));
                System.out.println(replace.substring(12, 28));
                System.out.println(replace.substring(28, 44));
                String temp = replace.substring(44, replace.length() - 8);
                int index = 0;
                while ((index = temp.lastIndexOf("ffff")) != -1) {
                    System.out.println(temp.substring(index));
                    System.out.println(temp.substring(index).length());
                    System.out.println(temp.substring(index).substring(0, 2));
                    System.out.println(temp.substring(index).substring(2, 4));
                    System.out.println(temp.substring(index).substring(4, 6));
                    System.out.println(temp.substring(index).substring(8, 16));
                    System.out.println(temp.substring(index).substring(16, 24));
                    System.out.println(temp.substring(index).substring(24, 32));
                    System.out.println(temp.substring(index).substring(32, 40));
                    System.out.println(temp.substring(index).substring(40, 48));
                    System.out.println(temp.substring(index).substring(56, 64));
                    System.out.println(temp.substring(index).substring(64, 72));
                    System.out.println(temp.substring(index).substring(72, 80));
                    System.out.println(temp.substring(index).substring(80, 96));
                    System.out.println(temp.substring(index).substring(96, 112));
                    temp = temp.substring(0, index);
                }
                System.out.println(replace.substring(replace.length() - 8, replace.length() - 4));
                System.out.println(replace.substring(replace.length() - 4, replace.length()));
            }
            //通过Socket对象得到输出流，构造BufferedWrite对象
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            //模拟了http的请求头信息
            writer.write("HTTP/1.1 200 OK \r\n Content-Type:text/html \r\n charset=UTF-8\r\n\r\n ");
            //写一些html的体
            writer.write("<html><head><title>http请求</title></head><body><h1>这是一个HTTP请求！</h1></body></html>");
            //刷新输出流，使得数据立马发送
            writer.flush();
            //关闭
            reader.close();
            writer.close();
            socket.close();
        }
    }

}