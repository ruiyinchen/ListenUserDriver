package com.jugan;

import com.jugan.analysis.AnalysisData;
import com.jugan.tools.Utilty;

/**
 * @Author CL
 * @Date 2019/5/27-15:33
 */
public class TestDemo {

    public static void main(String[] args) {
        byte[] buf = {(byte) 0x40, (byte) 0x40, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x01, (byte) 0x3A, (byte) 0x2A, (byte) 0x0B, (byte) 0x1B, (byte) 0x05, (byte) 0x13, (byte) 0xEE, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xE9, (byte) 0x03, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x09, (byte) 0x00, (byte) 0x02, (byte) 0x15, (byte) 0x02, (byte) 0x41, (byte) 0x3A, (byte) 0x2A, (byte) 0x0B, (byte) 0x1B, (byte) 0x05, (byte) 0x13, (byte) 0x41, (byte) 0x3A, (byte) 0x2A, (byte) 0x0B, (byte) 0x1B, (byte) 0x05, (byte) 0x14, (byte) 0x82, (byte) 0x23, (byte) 0x23};

        AnalysisData.getInfo().analysis(buf);

        /*System.out.println(Utilty.parseByte2HexStr(as()));
        int k = 28;
        byte[] dataInfo = new byte[buf.length - 32];//创建应用数据单元信息体总数组
        for (int i =0;i <buf.length-32;i++)
            dataInfo[i] = buf[++k];

        int kk = -1;
        for (int i =0;i<2;i++){
            byte[] newByte = new byte[7];
            for (int j = 0;j < newByte.length;j++){
                newByte[j] = dataInfo[++kk];
            }
            System.out.println(Utilty.parseByte2HexStr(newByte));
        }*/
    }


    public static byte[] as(){
        byte[] ss = {(byte) 0x41, (byte) 0x3A, (byte) 0x2A, (byte) 0x0B, (byte) 0x1B, (byte) 0x05, (byte) 0x13, (byte) 0x41, (byte) 0x3A, (byte) 0x2A, (byte) 0x0B, (byte) 0x1B, (byte) 0x05, (byte) 0x14};
        return ss;
    }
}