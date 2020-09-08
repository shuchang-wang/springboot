package com.alibaba.interview;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author: WSC
 * @Create 2020/9/2 17:45
 */
public class UDPSendDemo {

    public static void main(String[] args) throws Exception {
        //创建一个DatagramSocket实例
        DatagramSocket datagramSocket = new DatagramSocket();
        //使用键盘输入构建一个BufferedReader
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            //转成byte
            byte[] bytes = line.getBytes();
            //创建一个用于发送的DatagramPacket对象
            DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("127.0.0.1"), 10005);
            //发送数据包
            datagramSocket.send(datagramPacket);
            if ("88".equals(line)) {//当输入88时结束发送
                break;
            }
        }
        System.out.println("UDP发送端已发送结束。。。。。。。。。");
        //关闭
        datagramSocket.close();
    }

}