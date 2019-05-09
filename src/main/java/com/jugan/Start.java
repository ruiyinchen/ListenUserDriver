package com.jugan;

import com.jugan.thread.ThreadServer;

/**
 * @Author CL
 * @Date 2019/4/3-9:35
 */
public class Start {//设置监听的端口号
    private static int port = 6503;

    public static void main(String[] args){
        new ThreadServer(port).start();//启动线程
    }
}
