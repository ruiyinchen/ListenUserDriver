package com.jugan.analysis;


import com.jugan.entity.Info;
import com.jugan.entity.upBound.BuildParts;
import com.jugan.entity.upBound.BuildSystem;
import com.jugan.entity.upBound.UserFunction;
import com.jugan.tools.Convert;
import com.jugan.tools.Utilty;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户信息类型
 * @Author CL
 * @Date 2019/3/25-13:45
 */
public class InfoType {

    private static int k = -1;

    /**
     * /根据信息对象进行解析
     * @param infoType 数据单元类型
     * @param buf 数据
     * @param infos Info对象
     */
    public static void infoType(int infoType, byte[] buf, Info infos){

        switch (infoType){
            case 1://上传建筑消防设施系统状态(共占10个字节,信息体占4个字节,时间占6个字节)
                BuildSystem system = new BuildSystem();
                system.setSystemType(buf[++k]);
                system.setSystemAddress(buf[++k]);
                byte[] systemState = InfoType.toLowByte(buf,2);
                int[] buildSys = Convert.toBinaryString(Integer.parseInt(Utilty.parseByte2HexStr(systemState)));
                system.setReserve1(buildSys[0]);
                system.setReserve0(buildSys[1]);
                system.setReset(buildSys[2]);
                system.setConfigure(buildSys[3]);
                system.setManualOrAutomatic(buildSys[4]);
                system.setMainLine(buildSys[5]);
                system.setEps(buildSys[6]);
                system.setMps(buildSys[7]);
                system.setDelayed(buildSys[8]);
                system.setFeedback(buildSys[9]);
                system.setStartOrStop(buildSys[10]);
                system.setSupervise(buildSys[11]);
                system.setShield(buildSys[12]);
                system.setFault(buildSys[13]);
                system.setFireAlarm(buildSys[14]);
                system.setFunctionType(buildSys[15]);
                //解析时间
                String buildSystemTime = InfoType.byteToDate(InfoType.establishTimeByte(buf,6));
                system.setDate(buildSystemTime);
                //添加到infos中
                List<BuildSystem> buildSystems = new ArrayList<>();
                buildSystems.add(system);
                infos.setBuildSystems(buildSystems);

                //System.out.println("上传建筑消防设施系统状态(上行):" + buildSystemTime);

                break;
            case 2://上传建筑消防设施部件运行状态(共占46个字节,信息体占40个字节,时间占6个字节)
                BuildParts parts = new BuildParts();
                parts.setSystemType(buf[++k]);
                parts.setSystemAddress(buf[++k]);
                parts.setPartsType(buf[++k]);
                //String partsAddress = InfoType.toAddress(InfoType.establishTimeByte(buf,4));
                String partsAddress = Utilty.parseByte2HexStr(InfoType.toLowOut(InfoType.establishTimeByte(buf,4)));
                parts.setPartsAddress(partsAddress);
                byte[] partsState = InfoType.toLowByte(buf,2);
                int[] buildParts = Convert.toBinaryString(Integer.parseInt(Utilty.parseByte2HexStr(partsState)));
                parts.setReserve6(buildParts[0]);
                parts.setReserve5(buildParts[1]);
                parts.setReserve4(buildParts[2]);
                parts.setReserve3(buildParts[3]);
                parts.setReserve2(buildParts[4]);
                parts.setReserve1(buildParts[5]);
                parts.setReserve0(buildParts[6]);
                parts.setPowerSupply(buildParts[7]);
                parts.setDelayed(buildParts[8]);
                parts.setFeedback(buildParts[9]);
                parts.setStartOrStop(buildParts[10]);
                parts.setSupervise(buildParts[11]);
                parts.setShield(buildParts[12]);
                parts.setFault(buildParts[13]);
                parts.setFireAlarm(buildParts[14]);
                parts.setFunctionType(buildParts[15]);
                //解析部件说明(未解析完成,暂时全部保存)
                byte[] partsExplains = InfoType.establishTimeByte(buf,31);
                parts.setPartsExplain(Utilty.parseByte2HexStr(partsExplains));

                //解析时间
                String buildPartsTime = InfoType.byteToDate(InfoType.establishTimeByte(buf,6));
                parts.setDate(buildPartsTime);
                //添加到infos中
                List<BuildParts> buildPartList =new ArrayList<>();
                buildPartList.add(parts);
                infos.setBuildParts(buildPartList);
                break;
            case 21://上传用户信息传输装置运行状态(上行)
                int[] userFun = Convert.toBinaryString(buf[++k]);
                UserFunction userFunction = new UserFunction();
                userFunction.setReserve(userFun[0]);
                userFunction.setLine(userFun[1]);
                userFunction.setCommunication(userFun[2]);
                userFunction.setEps(userFun[3]);
                userFunction.setMps(userFun[4]);
                userFunction.setFault(userFun[5]);
                userFunction.setFireAlarm(userFun[6]);
                userFunction.setMonitor(userFun[7]);
                //解析时间
                String userFunTime = InfoType.byteToDate(InfoType.establishTimeByte(buf,6));
                userFunction.setDate(userFunTime);
               // System.out.println("上传用户信息传输装置运行状态(上行):" + userFunTime);
                //添加到Info
                List<UserFunction> userFunctions = new ArrayList<>();
                userFunctions.add(userFunction);
                infos.setUserFunctions(userFunctions);
                break;
            case 28://上传用户信息传输装置系统时间(上行)
                String userSystemTime = InfoType.byteToDate(InfoType.establishTimeByte(buf,6));
               // System.out.println("上传用户信息传输装置系统时间(上行):" + userSystemTime);
                break;
        }
        //解析完成时让k值回到初始值,否则下次调用是会数组越界(这就是使用static的坏处啊(-_-`),猛男落泪)
        k = -1;
    }

    /**
     * 将byte数组反转再转成string
     * @param buf 时间byte数组
     * @return String
     */
    private static String byteToDate(byte[] buf){

        StringBuffer sb = new StringBuffer();
        for (int i = buf.length-1;i>=0;i--){
            String str = String.valueOf(Integer.parseInt(Utilty.parseByte2HexStr(new byte[]{buf[i]}),16));
            if (str.length() == 1)
                str = "0"+ str;
            sb.append(str);
        }
        return sb.toString();

    }

    /**
     * 创建自定义长度byte数组
     * @param buf 数组
     * @return
     */
    public static byte[] establishTimeByte(byte[] buf,int len){
        if (buf == null) return null;
        byte[] bytes = new byte[len];
        for (int i = 0;i < bytes.length;i++)
            bytes[i] = buf[++k];

        return bytes;
    }

    /**
     * 低字节转换
     * @param buf 之前数组
     * @param length 数组长度
     * @return 低字节数组
     */
    public static byte[] toLowByte(byte[] buf,int length){
        if (buf == null) return null;
        byte[] bytes = new byte[length];
        int h = 1;//低字节传输
        for (int i = bytes.length;i > 0;i--) {
            bytes[h] = buf[++k];
            h -= 1;
        }
        return bytes;
    }



    /**
     * 低字节输出
     * @param bufs 原数组
     * @return
     */
    public static byte[] toLowOut(byte[] bufs){
        byte[] buf = new byte[bufs.length];
        int h = bufs.length -1 ;
        for (int i = 0;i < buf.length;i++){
            buf[i] = bufs[h];
            h = h - 1;
        }
        return buf;
    }
    /**
     * 将数组转成地址(按低字节输出)
     * 格式 [127.0.0.1]
     * @param buf byte[]
     * @return String
     */
    public static String toAddress(byte[] buf){
        StringBuffer sb = new StringBuffer();
        for (int i = buf.length-1;i>=0;i--){
            String str = String.valueOf(Integer.parseInt(Utilty.parseByte2HexStr(new byte[]{buf[i]}),16));
            str = str + ".";

            sb.append(str);
        }
        String str = sb.toString();
        return str.substring(0,str.length()-1);
    }



}
