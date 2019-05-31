package com.jugan.thread;

import com.jugan.analysis.AnalysisData;
import com.jugan.entity.Info;
import com.jugan.entity.type.UserRun;
import com.jugan.entity.type.UserSystemTime;
import com.jugan.json.ConvertJson;
import com.jugan.tools.Utilty;
import com.fasterxml.jackson.databind.node.ObjectNode;

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
                //验证检验和
                //获得检验的数组
                byte[] newCrcs = new byte[buf.length - 5];//数组长度为总长度减去头和尾四个字节再减去校验和一个字节
                int num = 1;
                for (int i = 0;i < newCrcs.length;i++)
                    newCrcs[i] = buf[++num];
                int ss = Utilty.sumCheck(newCrcs);//获得该byte数组的校验和
                //System.out.println("\nss:"+ss);
                //获取原数组的校验和
                byte[] crcs = {buf[buf.length-3]};
                int crc = Integer.parseInt(Utilty.parseByte2HexStr(crcs),16);
                //校验和进行比较,结果不一致则不解析数据
                if (ss == crc ){
                    System.out.println("--------> 校验结果一致,开始进行数据解析(๑•̀ㅂ•́)و✧ <--------");
                    //解析数据
                    Info info = AnalysisData.getInfo().analysis(buf);
                    System.out.print("系统时间:"+info.getTime());
                    if (count == 38){
                        for (UserSystemTime userSystemTime :info.getUserSystemTimes()){
                            System.out.println("\t\t\t用户系统时间:"+userSystemTime.getTime());
                        }
                    }else {
                        for (UserRun userRun :info.getUserRuns()){
                            System.out.println("\t\t\t火警:"+userRun.getFireAlarm()+"\t\t\t时间:"+userRun.getTime());
                        }
                    }
                    //封装json体(未完成)
                    //ObjectNode node = ConvertJson.getJson().toJsonNode(info);
                }else {
                    System.out.println("--------> 校验结果不一致,不对数据进行解析(#_#) <--------");
                }
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