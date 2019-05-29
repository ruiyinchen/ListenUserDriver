package com.jugan;

import com.jugan.analysis.AnalysisData;
import com.jugan.analysis.Resolve;
import com.jugan.entity.Info;
import com.jugan.entity.type.UserRun;
import com.jugan.entity.type.UserSystemTime;
import com.jugan.tools.Convert;
import com.jugan.tools.Utilty;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author CL
 * @Date 2019/5/27-15:33
 */
public class TestDemo {

    public static void main(String[] args) {
        byte[] buf = {(byte) 0x40, (byte) 0x40, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x01, (byte) 0x3A, (byte) 0x2A, (byte) 0x0B, (byte) 0x1B, (byte) 0x05, (byte) 0x13, (byte) 0xEE, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xE9, (byte) 0x03, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x09, (byte) 0x00, (byte) 0x02, (byte) 0x15, (byte) 0x02, (byte) 0x41, (byte) 0x3A, (byte) 0x2A, (byte) 0x0B, (byte) 0x1B, (byte) 0x05, (byte) 0x13, (byte) 0x41, (byte) 0x3A, (byte) 0x2A, (byte) 0x0B, (byte) 0x1B, (byte) 0x05, (byte) 0x14, (byte) 0x82, (byte) 0x23, (byte) 0x23};

        Info info = AnalysisData.getInfo().analysis(buf);
        System.out.println("高莉:"+info.getTime());
        for (UserRun userRun :info.getUserRuns()){
            System.out.println("火警:"+userRun.getFireAlarm()+"\t\t时间:"+userRun.getTime());
        }

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

    public static List<UserRun> fff(int infoNum,int infoLength,byte[] dataInfo){
        int kk = -1,k = -1;
        List<UserRun> userFunctions = new ArrayList<>();
        for (int i =0;i < infoNum;i++){//根据信息对象数目循环进行解析
            byte[] newByte = new byte[infoLength];
            for (int j = 0;j < newByte.length;j++){
                newByte[j] = dataInfo[++kk];
            }
            System.out.println(Utilty.parseByte2HexStr(newByte));
            int[] userFun = Convert.toBinaryString(newByte[++k]);
            UserRun userFunction = new UserRun();
            userFunction.setReserve(userFun[0]);
            userFunction.setLine(userFun[1]);
            userFunction.setCommunication(userFun[2]);
            userFunction.setEps(userFun[3]);
            userFunction.setMps(userFun[4]);
            userFunction.setFault(userFun[5]);
            userFunction.setFireAlarm(userFun[6]);
            userFunction.setMonitor(userFun[7]);
            //解析时间
            String userFunTime = Utilty.byteToDate(Utilty.establishTimeByte(newByte,6));
            userFunction.setTime(userFunTime);
            //System.out.println("上传用户信息传输装置运行状态(上行):" + userFunTime);
            //添加到Infos
            userFunctions.add(userFunction);
            k = -1;
        }
        return userFunctions;
    }

    public static byte[] as(){
        byte[] ss = {(byte) 0x41, (byte) 0x3A, (byte) 0x2A, (byte) 0x0B, (byte) 0x1B, (byte) 0x05, (byte) 0x13, (byte) 0x41, (byte) 0x3A, (byte) 0x2A, (byte) 0x0B, (byte) 0x1B, (byte) 0x05, (byte) 0x14};
        return ss;
    }
}
