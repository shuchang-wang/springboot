package com.alibaba.interview;

import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author: WSC
 * @Create 2020/9/2 14:50
 */
public class TcpClient {

    public static void main(String[] args) throws Exception {
        String msg = "7e 7e 01 00 18 01 01 00 00 00 00 00 00 00 66 f7 80 a2 73 01 00 00 ff ff 04 00 37 00 00 00 46 82 33 41 c7 4f 28 40 4e dd 53 40 fa 2c ae 43 37 47 88 3c 00 00 b4 42 00 00 80 bf 00 00 80 bf f3 d7 b6 c6 6e 92 5a 40 56 e2 7e f9 ac 85 3d 40 ff ff 04 00 35 00 00 00 46 d2 2e 41 36 ee 40 40 19 61 4e 40 35 92 ab 43 d1 45 6e 3f 82 04 1f 43 00 00 80 bf 00 00 80 bf 6e df 41 44 77 92 5a 40 dd 20 e0 ac 7c 85 3d 40 ff ff 04 00 38 00 00 00 d0 09 d0 40 6a 23 0e 40 31 1a 2f 40 6f 14 ae 43 8e d7 87 40 4c 56 32 43 00 00 80 bf 00 00 80 bf a9 fa a2 c2 72 92 5a 40 94 9d c9 d1 a3 85 3d 40 ff ff 03 00 36 00 00 00 15 fa 8f 40 b8 a8 f4 3f ad 30 e6 3f 52 28 9b 43 8d 30 83 40 fa 77 09 43 00 00 80 bf 00 00 80 bf 1e 66 63 34 7c 92 5a 40 70 65 99 cd 8a 85 3d 40 ff ff 04 00 39 00 00 00 0f 0a 05 41 de 21 2e 40 e9 5e 46 40 b4 68 9b 43 bf 48 e5 3f b3 d3 8c 43 00 00 80 bf 00 00 80 bf d6 3f 38 92 7e 92 5a 40 f5 a5 e5 91 86 85 3d 40 72 fe 7e 7d";
        //创建一个Socket,跟本机的8888端口进行连接
        Socket socket = new Socket("127.0.0.1", 8888);
        socket.setReuseAddress(true);
        socket.setSoLinger(true, 5);
        socket.setSendBufferSize(4*1024);
        socket.setReceiveBufferSize(1024);
        socket.setTcpNoDelay(true);
        socket.setSoTimeout(60*000);
        socket.setKeepAlive(true);
        //使用Socket创建一个PrintWriter进行写数据
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        //发送数据
        printWriter.println(msg);
        //刷新一下，使得服务立马可以收到请求信息
        printWriter.flush();
        //printWriter.println(msg);

        //关闭资源
        printWriter.close();
        socket.close();
    }

}