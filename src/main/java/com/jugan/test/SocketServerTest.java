package com.jugan.test;

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerTest {
    private static final int PORT = 5209;

    public static void test() {
        ServerSocket server = null;
        Socket socket = null;
        DataOutputStream out = null;
        try {
            server = new ServerSocket(PORT);
            socket = server.accept();
            out = new DataOutputStream(socket.getOutputStream());
            while (true) {
                Thread.sleep(1000);
                out.writeUTF(getRandomStr());
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getRandomStr() {
        String str = "";
        int ID = (int) (Math.random() * 30);
        int x = (int) (Math.random() * 200);
        int y = (int) (Math.random() * 300);
        int z = (int) (Math.random() * 10);
        str = "ID:" + ID + "/x:" + x + "/y:" + y + "/z:" + z;
        return str;
    }

    public static void main(String[] args) {
        test();
    }
}