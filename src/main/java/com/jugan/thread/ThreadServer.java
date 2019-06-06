package com.jugan.thread;

import com.jugan.analysis.AnalysisData;
import com.jugan.analysis.HandleHEX;
import com.jugan.entity.Info;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadServer {
    private int port;
    private ExecutorService service = Executors.newFixedThreadPool(3);

    public ThreadServer(int port) {
        this.port = port;
    }

    public void start() {
        ServerSocket serverSocket = null;
        Socket socket = null;

        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                socket = serverSocket.accept();
                service.submit(new ServerHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static int k = 0;//数组下标
    public static class ServerHandler implements Runnable {
        private Socket socket;
        public ServerHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {

            try {
                System.out.print("Accept from: " + socket.getRemoteSocketAddress());
                InputStream inputStream = socket.getInputStream();//获取InputStream对象

                int count = 0;//获取字节流长度
                // 本地程序调用available()方法有时得到0，这可能是对方还没有响应，也可能是对方已经响应了，但是数据还没有送达本地。
                // 如果对方发送了1000个字节给你，也许分成3批到达，这你就要调用3次available()方法才能将数据总数全部得到
                while (count == 0)
                    count = inputStream.available();
                System.out.println("\t\tcount值:" + count);


                byte[] buf = new byte[count];//创建数组
                inputStream.read(buf);//字节流写入

                for (int i = 0 ;i < buf.length;i++)
                    System.out.print(buf[i] + " ");
                System.out.println();

                byte[] bytes = HandleHEX.toHandle().toArray(buf);

                //解析数据
                Info info = AnalysisData.getAnalysisData().analysis(bytes);


                System.out.println();



            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    if (socket != null) {
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}