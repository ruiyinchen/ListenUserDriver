package com.jugan.test;

import com.jugan.tools.Utilty;

/**
 * @Author CL
 * @Date 2019/4/3-14:41
 */
public class Test {

    public static void main(String[] args) {
        byte[] buf = sdfs();//原数组
        //创建信息体数组
        byte[] bytes = new byte[buf.length - 32];
        int k = 28;
        for (int i = 0; i < bytes.length; i++){//从原数组信息体部分赋给信息体数组
            bytes[i] = buf[++k];
            System.out.print(bytes[i] + " ");
        }
        System.out.println();
        int kk = -1;
        int numlen = buf[27];
        int ff = buf[28];
        //新数组长度
        int len  = sa(numlen);
        boolean isOk = true;
        int length = 0;
        for (int j = 0;j<ff;j++){
            byte[] infos = new byte[len];
            for (int i = 0; i < infos.length; i++){
                infos[i] = bytes[++kk];
                System.out.print(infos[i] + " ");
            }
            System.out.println();
        }


      /*  do {
            byte[] infos = new byte[len];
            for (int i = 0; i < infos.length; i++)
                infos[i] = bytes[++kk];
            int s = length+len;
            length = bytes.length - s;
            if (length == 0)
                isOk = false;
        } while (isOk);*/

    }
    public static int sa(int num){
        int ss = 0;
        switch (num){
            case 1:
                ss = 10;
                break;
            case 2:
                ss = 46;
                break;
            case 21:
                ss = 7;
                break;
            case 28:
                ss = 6;
                break;
        }
        return ss;
    }

    public static byte[] sdfs() {
        byte[] buf = {
                (byte) 0x40, (byte) 0x40, (byte) 0xF4, (byte) 0x02, (byte) 0x01, (byte) 0x01, (byte) 0x30,
                (byte) 0x15, (byte) 0x0F, (byte) 0x03, (byte) 0x04, (byte) 0x13, (byte) 0xEE, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xE9, (byte) 0x03, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x08, (byte) 0x00, (byte) 0x02, (byte) 0x1C,
                (byte) 0x02, (byte) 0x30, (byte) 0x15, (byte) 0x0F, (byte) 0x03, (byte) 0x04, (byte) 0x13,
                (byte) 0x31, (byte) 0x15, (byte) 0x0F, (byte) 0x03, (byte) 0x03, (byte) 0x13, (byte) 0xD5,
                (byte) 0x23, (byte) 0x23
        };
        return buf;
    }
}
