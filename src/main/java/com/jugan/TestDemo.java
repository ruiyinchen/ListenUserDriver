package com.jugan;

import com.jugan.analysis.AnalysisData;
import com.jugan.analysis.Resolve;
import com.jugan.entity.Info;
import com.jugan.entity.type.*;
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


        Info info = AnalysisData.getAnalysisData().analysis(userConfigure());
        System.out.println("系统时间:"+info.getTime());


       /* //同步用户信息传输装置时钟
        for (UserSyncClock time:info.getUserSyncClocks()){
            System.out.println("时间:"+time.getTime());

        }*/



        /*//查岗命令
        for (InspectTheSentriesCommand command :info.getSentriescommands()){
            System.out.println("预留:"+command.getInspectTheSentries()+"\t\t\t时间:"+command.getTime());

        }*/

        /*//初始化用户信息传输装置
        for (UserInit init :info.getUserInits()){
            System.out.println("预留:"+init.getObligate()+"\t\t\t时间:"+init.getTime());

        }*/

        /*//用户信息传输装置系统时间
        for (UserSystemTime time:info.getUserSystemTimes()){
            System.out.println("时间:"+time.getTime());

        }*/


        //上传用户信息传输装置配置情况
        for (UserConfigure configure :info.getUserConfigures()){
            System.out.print("系统配置说明:"+configure.getExplanation());
            System.out.println("\t\t\t时间:"+configure.getTime());

        }

        /*//用户信息传输装置操作信息
        for (UserOperate operate:info.getUserOperates()){
            System.out.print("复位:"+operate.getReset()+"\t\t\t消音:"+operate.getNoisedamping()+"\t\t\t操作员:"+operate.getOperator());
            System.out.println("\t\t\t时间:"+operate.getTime());
        }*/



        /*//建筑消防设施系统时间
        for (BuildSystemTime time:info.getBuildSystemTimes()){
            System.out.println("时间:"+time.getTime());

        }*/


        /*//建筑消防设施部件配置情况
        for (BuildPartsConfigure configure:info.getBuildPartsConfigures()){
            System.out.print("系统类型标志:"+configure.getSystemType()+"\t\t\t系统地址:"+configure.getSystemAddress()+"\t\t\t部件地址:"+configure.getPartsAddress()+"\t\t\t部件说明:"+configure.getExplanation());
            System.out.println("\t\t\t时间:"+configure.getTime());

        }*/


        /*//建筑消防设施系统配置情况
        for (BuildSystemConfigure configure :info.getBuildSystemConfigures()){
            System.out.print("系统类型标志:"+configure.getSystemType()+"\t\t\t系统地址:"+configure.getSystemAddress()+"\t\t\t系统配置说明:"+configure.getExplanation());
            System.out.println("\t\t\t时间:"+configure.getTime());

        }*/

        /*//建筑消防设施软件版本
        for (BuildSoftware software :info.getBuildSoftwares()){
            System.out.print("系统类型标志:"+software.getSystemType()+"\t\t\t系统地址:"+software.getSystemAddress());
            System.out.print("\t\t\t主版本:"+software.getMainVersion()+"\t\t\t次版本:"+software.getSecondary());
            System.out.println("\t\t\t时间:"+software.getTime());
        }*/

       /* //建筑消防设施操作信息
        for (BuildOperate operate :info.getBuildOperates()){
            System.out.print("系统类型标志:"+operate.getSystemType()+"\t\t\t系统地址:"+operate.getSystemAddress());
            System.out.print("\t\t\t复位:"+operate.getReset()+"\t\t\t火警:"+operate.getPoliceExterminate());
            System.out.println("\t\t\t时间:"+operate.getTime());
        }*/



       /* //建筑消防设施部件模拟量值
        for (BuildPartsAnalog analog : info.getBuildPartsAnalogs()){
            System.out.print("系统类型标志:"+analog.getSystemType()+"\t\t\t系统地址:"+analog.getSystemAddress()+"\t\t\t部件地址:"+analog.getPartsAddress());
            System.out.print("\t\t\t模拟量类型:"+analog.getAnalogType()+"\t\t\t模拟量值:"+analog.getAnalogValues());
            System.out.println("\t\t\t时间:"+analog.getTime());
        }*/
       /* //建筑消防设施部件运行状态
        for (BuildPartsRun run:info.getBuildPartRuns()){
            System.out.print("系统类型标志:"+run.getSystemType()+"\t\t\t系统地址:"+run.getSystemAddress()+"\t\t\t部件地址:"+run.getPartsAddress());
            System.out.print("\t\t\t运行状态:"+run.getFunctionType()+"\t\t\t火警:"+run.getFireAlarm());
            System.out.println("\t\t\t时间:"+run.getTime());

        }*/



        /*//用户信息传输装置运行状态
        for (UserRun userRun :info.getUserRuns()){
            System.out.println("火警:"+userRun.getFireAlarm()+"\t\t时间:"+userRun.getTime());
        }*/
      /*  //建筑消防设施系统状态
        for (BuildSystem buildSystem :info.getBuildSystems()){
            System.out.print("系统类型标志:"+buildSystem.getSystemType()+"\t\t\t系统地址:"+buildSystem.getSystemAddress());
            System.out.print("\t\t\t运行状态:"+buildSystem.getFunctionType()+"\t\t\t火警:"+buildSystem.getFireAlarm());
            System.out.println("\t\t\t时间:"+buildSystem.getTime());
        }*/

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



    public static byte[] buildSystem(){
        byte[] buf = {(byte) 0x40, (byte) 0x40, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x01, (byte) 0x3A, (byte) 0x2A, (byte) 0x0B, (byte) 0x1B, (byte) 0x05, (byte) 0x13, (byte) 0xEE, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xE9, (byte) 0x03, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x09, (byte) 0x00, (byte) 0x02, (byte) 0x01, (byte) 0x02, (byte) 0x01, (byte) 0x01,(byte) 0x02, (byte) 0x03, (byte) 0x3A, (byte) 0x2A, (byte) 0x0B, (byte) 0x1B, (byte) 0x05, (byte) 0x14,(byte) 0x01, (byte) 0x01,(byte) 0x02, (byte) 0x03, (byte) 0x3A, (byte) 0x2A, (byte) 0x0B, (byte) 0x1B, (byte) 0x05, (byte) 0x14, (byte) 0x82, (byte)0x23, (byte) 0x23};
        return buf;
    }

    public static byte[] buildPartsRun(){
        byte[] buf = {(byte)0x40,(byte)0x40,(byte)0xA2,(byte)0x00,(byte)0x01,(byte)0x01,(byte)0x3B,(byte)0x24,(byte)0x0A,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0xEE,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0xE9,(byte)0x03,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x08,(byte)0x00,(byte)0x02,(byte)0x02,(byte)0x02,(byte)0x01,(byte)0x02,(byte)0x01,(byte)0x00,(byte)0x01,(byte)0x02,(byte)0x03,(byte)0x09,(byte)0x01,(byte)0x40,(byte)0x40,(byte)0xA2,(byte)0x00,(byte)0x01,(byte)0x01,(byte)0x3B,(byte)0x24,(byte)0x0A,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0xEE,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0xE9,(byte)0x03,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x08,(byte)0x00,(byte)0x02,(byte)0x1C,(byte)0x01,(byte)0x40,(byte)0x40,(byte)0x3A, (byte)0x2A,(byte)0x0B,(byte)0x1B,(byte)0x05,(byte)0x14,(byte)0x01,(byte)0x02,(byte)0x01,(byte)0x00,(byte)0x01,(byte)0x02,(byte)0x03,(byte)0x09,(byte)0x01,(byte)0x40,(byte)0x40,(byte)0xA2,(byte)0x00,(byte)0x01,(byte)0x01,(byte)0x3B,(byte)0x24,(byte)0x0A,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0xEE,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0xE9,(byte)0x03,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x08,(byte)0x00,(byte)0x02,(byte)0x1C,(byte)0x01,(byte)0x40,(byte)0x40,(byte)0x3A,(byte)0x2A,(byte)0x0B,(byte)0x1B,(byte)0x05,(byte)0x14,(byte)0xE3,(byte)0x23,(byte)0x23};
        return buf;
    }

    public static byte[] buildPartsAnalog(){
        byte[] buf = {(byte)0x40,(byte)0x40,(byte)0xA2,(byte)0x00,(byte)0x01,(byte)0x01,(byte)0x3B,(byte)0x24,(byte)0x0A,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0xEE,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0xE9,(byte)0x03,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x08,(byte)0x00,(byte)0x02,(byte)0x03,(byte)0x02,(byte)0x01,(byte)0x02,(byte)0x01,(byte)0x00,(byte)0x01,(byte)0x02,(byte)0x03,(byte)0x01,(byte)0xAD,(byte)0xCD,(byte)0x23,(byte)0x1B,(byte)0x0B,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0x01,(byte)0x02,(byte)0x01,(byte)0x00,(byte)0x01,(byte)0x02,(byte)0x03,(byte)0x01,(byte)0xAD,(byte)0xCD,(byte)0x23,(byte)0x1B,(byte)0x0B,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0xE3,(byte)0x23,(byte)0x23};
        return buf;
    }
    public static byte[] buildOperate(){
        byte[] buf = {(byte)0x40,(byte)0x40,(byte)0xA2,(byte)0x00,(byte)0x01,(byte)0x01,(byte)0x3B,(byte)0x24,(byte)0x0A,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0xEE,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0xE9,(byte)0x03,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x08,(byte)0x00,(byte)0x02,(byte)0x04,(byte)0x02,(byte)0x01,(byte)0x02,(byte)0x1A,(byte)0x01,(byte)0x23,(byte)0x1B,(byte)0x0B,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0x01,(byte)0x02,(byte)0x1A,(byte)0x01,(byte)0x23,(byte)0x1B,(byte)0x0B,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0xE3,(byte)0x23,(byte)0x23};
        return buf;
    }
    public static byte[] buildSoftware(){
        byte[] buf = {(byte)0x40,(byte)0x40,(byte)0xA2,(byte)0x00,(byte)0x01,(byte)0x01,(byte)0x3B,(byte)0x24,(byte)0x0A,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0xEE,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0xE9,(byte)0x03,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x08,(byte)0x00,(byte)0x02,(byte)0x05,(byte)0x02,(byte)0x01,(byte)0x02,(byte)0x01,(byte)0x02,(byte)0x23,(byte)0x1B,(byte)0x0B,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0x01,(byte)0x02,(byte)0x01,(byte)0x02,(byte)0x23,(byte)0x1B,(byte)0x0B,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0xE3,(byte)0x23,(byte)0x23};
        return buf;
    }

    public static byte[] buildSystemConfigure(){
        byte[] buf = {(byte)0x40,(byte)0x40,(byte)0xA2,(byte)0x00,(byte)0x01,(byte)0x01,(byte)0x3B,(byte)0x24,(byte)0x0A,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0xEE,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0xE9,(byte)0x03,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x08,(byte)0x00,(byte)0x02,(byte)0x06,(byte)0x02,(byte)0x01,(byte)0x02,(byte)0x02,(byte)0x02,(byte)0x02,(byte)0x23,(byte)0x1B,(byte)0x0B,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0x01,(byte)0x02,(byte)0x02,(byte)0x02,(byte)0x02,(byte)0x23,(byte)0x1B,(byte)0x0B,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0xE3,(byte)0x23,(byte)0x23};
        return buf;
    }

    public static byte[] buildPartsConfigure(){
        byte[] buf = {(byte)0x40,(byte)0x40,(byte)0xA2,(byte)0x00,(byte)0x01,(byte)0x01,(byte)0x3B,(byte)0x24,(byte)0x0A,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0xEE,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0xE9,(byte)0x03,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x08,(byte)0x00,(byte)0x02,(byte)0x07,(byte)0x02,(byte)0x01,(byte)0x02,(byte)0x01,(byte)0x00,(byte)0x01,(byte)0x02,(byte)0x03,(byte)0x23,(byte)0x1B,(byte)0x0B,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0x23,(byte)0x1B,(byte)0x0B,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0x23,(byte)0x1B,(byte)0x0B,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0x23,(byte)0x1B,(byte)0x0B,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0x23,(byte)0x1B,(byte)0x0B,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0x00,(byte)0x23,(byte)0x1B,(byte)0x0B,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0x01,(byte)0x02,(byte)0x01,(byte)0x00,(byte)0x01,(byte)0x02,(byte)0x03,(byte)0x23,(byte)0x1B,(byte)0x0B,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0x23,(byte)0x1B,(byte)0x0B,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0x23,(byte)0x1B,(byte)0x0B,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0x23,(byte)0x1B,(byte)0x0B,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0x23,(byte)0x1B,(byte)0x0B,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0x00,(byte)0x23,(byte)0x1B,(byte)0x0B,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0xE3,(byte)0x23,(byte)0x23};
        return buf;
    }

    public static byte[] buildSystemTime(){
        byte[] buf = {(byte)0x40,(byte)0x40,(byte)0xA2,(byte)0x00,(byte)0x01,(byte)0x01,(byte)0x3B,(byte)0x24,(byte)0x0A,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0xEE,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0xE9,(byte)0x03,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x08,(byte)0x00,(byte)0x02,(byte)0x08,(byte)0x02,(byte)0x23,(byte)0x1B,(byte)0x0B,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0x23,(byte)0x1B,(byte)0x0B,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0xE3,(byte)0x23,(byte)0x23};
        return buf;
    }

    public static byte[] userOperate(){
        byte[] buf = {(byte)0x40,(byte)0x40,(byte)0xA2,(byte)0x00,(byte)0x01,(byte)0x01,(byte)0x3B,(byte)0x24,(byte)0x0A,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0xEE,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0xE9,(byte)0x03,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x08,(byte)0x00,(byte)0x02,(byte)0x18,(byte)0x02,(byte)0x11,(byte)0x01,(byte)0x23,(byte)0x1B,(byte)0x0B,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0x11,(byte)0x01,(byte)0x23,(byte)0x1B,(byte)0x0B,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0xE3,(byte)0x23,(byte)0x23};
        return buf;
    }

    public static byte[] userSoftware(){
        byte[] buf = {(byte)0x40,(byte)0x40,(byte)0xA2,(byte)0x00,(byte)0x01,(byte)0x01,(byte)0x3B,(byte)0x24,(byte)0x0A,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0xEE,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0xE9,(byte)0x03,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x08,(byte)0x00,(byte)0x02,(byte)0x19,(byte)0x02,(byte)0x01,(byte)0x02,(byte)0x23,(byte)0x1B,(byte)0x0B,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0x01,(byte)0x02,(byte)0x23,(byte)0x1B,(byte)0x0B,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0xE3,(byte)0x23,(byte)0x23};
        return buf;
    }

    public static byte[] userConfigure(){
        byte[] buf = {(byte)0x40,(byte)0x40,(byte)0xA2,(byte)0x00,(byte)0x01,(byte)0x01,(byte)0x3B,(byte)0x24,(byte)0x0A,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0xEE,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0xE9,(byte)0x03,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x08,(byte)0x00,(byte)0x02,(byte)0x1a,(byte)0x02,(byte)0x03,(byte)0x02,(byte)0x02,(byte)0x02,(byte)0x23,(byte)0x1B,(byte)0x0B,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0x03,(byte)0x02,(byte)0x02,(byte)0x02,(byte)0x23,(byte)0x1B,(byte)0x0B,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0xE3,(byte)0x23,(byte)0x23};
        return buf;
    }


    public static byte[] userSystemTime(){
        byte[] buf = {(byte)0x40,(byte)0x40,(byte)0xA2,(byte)0x00,(byte)0x01,(byte)0x01,(byte)0x3B,(byte)0x24,(byte)0x0A,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0xEE,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0xE9,(byte)0x03,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x08,(byte)0x00,(byte)0x02,(byte)0x1c,(byte)0x02,(byte)0x23,(byte)0x1B,(byte)0x0B,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0x23,(byte)0x1B,(byte)0x0B,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0xE3,(byte)0x23,(byte)0x23};
        return buf;
    }
    public static byte[] userInit(){
        byte[] buf = {(byte)0x40,(byte)0x40,(byte)0xA2,(byte)0x00,(byte)0x01,(byte)0x01,(byte)0x3B,(byte)0x24,(byte)0x0A,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0xEE,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0xE9,(byte)0x03,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x08,(byte)0x00,(byte)0x02,(byte)0x59,(byte)0x02,(byte)0x00,(byte)0x23,(byte)0x1B,(byte)0x0B,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0x01,(byte)0x23,(byte)0x1B,(byte)0x0B,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0xE3,(byte)0x23,(byte)0x23};
        return buf;
    }


    public static byte[] command(){
        byte[] buf = {(byte)0x40,(byte)0x40,(byte)0xA2,(byte)0x00,(byte)0x01,(byte)0x01,(byte)0x3B,(byte)0x24,(byte)0x0A,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0xEE,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0xE9,(byte)0x03,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x08,(byte)0x00,(byte)0x02,(byte)0x5b,(byte)0x02,(byte)0x00,(byte)0x23,(byte)0x1B,(byte)0x0B,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0x01,(byte)0x23,(byte)0x1B,(byte)0x0B,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0xE3,(byte)0x23,(byte)0x23};
        return buf;
    }

    public static byte[] clock(){
        byte[] buf = {(byte)0x40,(byte)0x40,(byte)0xA2,(byte)0x00,(byte)0x01,(byte)0x01,(byte)0x3B,(byte)0x24,(byte)0x0A,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0xEE,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0xE9,(byte)0x03,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x08,(byte)0x00,(byte)0x02,(byte)0x5a,(byte)0x02,(byte)0x23,(byte)0x1B,(byte)0x0B,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0x23,(byte)0x1B,(byte)0x0B,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0xE3,(byte)0x23,(byte)0x23};
        return buf;
    }
}
