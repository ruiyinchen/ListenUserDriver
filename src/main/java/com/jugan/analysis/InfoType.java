package com.jugan.analysis;


import com.jugan.entity.Info;
import com.jugan.entity.type.*;
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


    private static InfoType info = new InfoType();

    public static InfoType getInfoType() {
        return info;
    }
    /**
     * /根据信息对象进行解析
     * @param infoType 数据单元类型
     * @param buf 数据
     * @param infos Info对象
     */
    public static void infoType(int infoType, byte[] buf, Info infos){
       // System.out.println("信息体数组:" + Utilty.parseByte2HexStr(buf));
        int k = -1;
        switch (infoType){
            case 1://上传建筑消防设施系统状态(共占10个字节,信息体占4个字节,时间占6个字节)
                BuildSystem system = new BuildSystem();
                system.setSystemType(buf[++k]);//系统类型标志
                system.setSystemAddress(buf[++k]);//系统地址
                byte[] systemState = InfoType.getInfoType().toLowByte(buf,2,k);//系统状态
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
                String buildSystemTime = InfoType.getInfoType().byteToDate(InfoType.getInfoType().establishTimeByte
                        (buf,6,k));
                system.setTime(buildSystemTime);
                //添加到infos中
                List<BuildSystem> buildSystems = new ArrayList<>();
                buildSystems.add(system);
                infos.setUpBuildSystems(buildSystems);

                System.out.println("上传建筑消防设施系统状态(上行):" + buildSystemTime);

                break;
            case 2://上传建筑消防设施部件运行状态(共占46个字节,信息体占40个字节,时间占6个字节)
                BuildPartsRun parts = new BuildPartsRun();
                parts.setSystemType(buf[++k]);//系统类型标志
                parts.setSystemAddress(buf[++k]);//系统地址
                parts.setPartsType(buf[++k]);//部件类型
                String partsAddress = Utilty.parseByte2HexStr(InfoType.getInfoType().establishTimeByte(buf,4,k));//部件地址
                parts.setPartsAddress(partsAddress);
                byte[] partsState = InfoType.getInfoType().toLowByte(buf,2,k);//部件状态
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
                byte[] partsExplains = InfoType.getInfoType().establishTimeByte(buf,31,k);
                parts.setPartsExplain(Utilty.parseByte2HexStr(partsExplains));

                //解析时间
                String buildPartsTime = InfoType.getInfoType().byteToDate(InfoType.getInfoType().establishTimeByte
                        (buf,6,k));
                parts.setTime(buildPartsTime);
                //添加到infos中
                List<BuildPartsRun> buildPartList =new ArrayList<>();
                buildPartList.add(parts);
                infos.setUpBuildPartRuns(buildPartList);
                System.out.println("传建筑消防设施部件运行状态"+buildPartsTime);
                break;

            case 3://上传建筑消防设施部件模拟量值(共16 个字节,信息体 10个字节,时间6个字节)
                BuildPartsAnalog analog = new BuildPartsAnalog();
                analog.setSystemType(buf[++k]);//系统状态
                analog.setSystemAddress(buf[++k]);//系统地址
                analog.setPartsType(buf[++k]);//部件类型
                //部件地址
                analog.setPartsAddress(Utilty.parseByte2HexStr(InfoType.getInfoType().establishTimeByte(buf,4,k)));
                analog.setAnalogType(buf[++k]);//模拟量类型
                //模拟量值<获得低字节数组>
                byte[] analogValues = InfoType.getInfoType().toLowOut(InfoType.getInfoType().establishTimeByte(buf,2,
                    k));
                //模拟量值<有符号>
                int analogValue = Utilty.num2Hex(Integer.parseInt(Utilty.parseByte2HexStr(analogValues),16),2);
                analog.setAnalogValues(analogValue);
                //解析时间
                String buildPartsAnalogTime = InfoType.getInfoType().byteToDate(InfoType.getInfoType().establishTimeByte
                        (buf,6,k));
                analog.setTime(buildPartsAnalogTime);
                //添加到infos中
                List<BuildPartsAnalog> upAnalogList = new ArrayList<>();
                upAnalogList.add(analog);
                infos.setUpBuildPartsAnalogs(upAnalogList);

                break;
            case 4://上传建筑消防设施操作信息(共10个字节,信息体4个字节,时间6个字节)
                BuildOperate operate = new BuildOperate();
                operate.setSystemType(buf[++k]);//系统类型
                operate.setSystemAddress(buf[++k]);//系统地址
                //获得操作标志
                byte[] buildOperate = InfoType.getInfoType().toLowByte(buf,1,k);
                int[] operateValues = Convert.toBinaryString(Integer.parseInt(Utilty.parseByte2HexStr(buildOperate)));
                operate.setReset(operateValues[7]);
                operate.setNoisedamping(operateValues[6]);
                operate.setManualAlarm(operateValues[5]);
                operate.setPoliceExterminate(operateValues[4]);
                operate.setSelfCheck(operateValues[3]);
                operate.setValidation(operateValues[2]);
                operate.setTest(operateValues[1]);
                operate.setObligate(operateValues[0]);

                operate.setOperator(buf[++k]);
                String buildOperateTime = InfoType.getInfoType().byteToDate(InfoType.getInfoType().establishTimeByte
                        (buf,6,k));
                operate.setTime(buildOperateTime);
                //添加到infos中
                List<BuildOperate> operateList = new ArrayList<>();
                operateList.add(operate);
                infos.setUpBuildOperates(operateList);

                break;
            case 5://上传建筑消防设施软件版本(共10个字节,信息体4个字节,时间6个字节)
                BuildSoftware software = new BuildSoftware();
                software.setSystemType(buf[++k]);
                software.setSystemAddress(buf[++k]);
                software.setMainVersion(buf[++k]);
                software.setSecondary(buf[++k]);
                String buildSoftwareTime = InfoType.getInfoType().byteToDate(InfoType.getInfoType().establishTimeByte
                        (buf,6,k));
                software.setTime(buildSoftwareTime);
                //添加到infos中
                List<BuildSoftware> softwareList = new ArrayList<>();
                softwareList.add(software);
                infos.setUpBuildSoftwares(softwareList);


                break;
            case 6://上传建筑消防设施系统配置情况(共n+9,信息体n+3个字节,时间6个字节)
                BuildSystemConfigure systemConfigure = new BuildSystemConfigure();
                systemConfigure.setSystemType(buf[++k]);
                systemConfigure.setSystemAddress(buf[++k]);
                int explanation = buf[++k];
                systemConfigure.setExplanationLen(explanation);
                systemConfigure.setExplanation(Utilty.parseByte2HexStr(InfoType.getInfoType().establishTimeByte(buf,
                        explanation,k)));
                String buildSystemConfigureTime = InfoType.getInfoType().byteToDate(InfoType.getInfoType().establishTimeByte
                        (buf,6,k));
                systemConfigure.setTime(buildSystemConfigureTime);
                //添加到infos中
                List<BuildSystemConfigure> systemConfigureList = new ArrayList<>();
                systemConfigureList.add(systemConfigure);
                infos.setUpBuildSystemConfigures(systemConfigureList);

                break;
            case 7://上传建筑消防设施部件配置情况(共44个字节,信息体38个字节,时间6个字节)
                BuildPartsConfigure partsConfigure = new BuildPartsConfigure();
                partsConfigure.setSystemType(buf[++k]);
                partsConfigure.setSystemAddress(buf[++k]);
                partsConfigure.setPartsType(buf[++k]);
                partsConfigure.setPartsAddress(Utilty.parseByte2HexStr(InfoType.getInfoType().toLowOut(InfoType
                        .getInfoType().establishTimeByte(buf,4,k))));
                partsConfigure.setExplanation(Utilty.parseByte2HexStr(InfoType.getInfoType().establishTimeByte(buf,
                        31,k)));
                String buildPartsConfigureTime = InfoType.getInfoType().byteToDate(InfoType.getInfoType().establishTimeByte
                        (buf,6,k));
                partsConfigure.setTime(buildPartsConfigureTime);
                //添加到infos中
                List<BuildPartsConfigure> partsConfigureList = new ArrayList<>();
                partsConfigureList.add(partsConfigure);
                infos.setUpBuildPartsConfigures(partsConfigureList);

                break;
            case 8://上传建筑消防设施系统时间(时间6个字节)
                BuildSystemTime systemTime = new BuildSystemTime();
                String buildSystemTimes = InfoType.getInfoType().byteToDate(InfoType.getInfoType().establishTimeByte
                        (buf,6,k));
                systemTime.setTime(buildSystemTimes);
                List<BuildSystemTime> systemTimeList = new ArrayList<>();
                systemTimeList.add(systemTime);
                infos.setUpBuildSystemTimes(systemTimeList);

                break;
            case 21://上传用户信息传输装置运行状态(共7个字节,信息体1个字节,时间6个字节)
                int[] userFun = Convert.toBinaryString(buf[++k]);
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
                String userFunTime = InfoType.getInfoType().byteToDate(InfoType.getInfoType().establishTimeByte(buf,6,
                        k));
                userFunction.setTime(userFunTime);
                System.out.println("上传用户信息传输装置运行状态(上行):" + userFunTime);
                //添加到Info
                List<UserRun> userFunctions = new ArrayList<>();
                userFunctions.add(userFunction);
                infos.setUpUserRuns(userFunctions);
                break;
            case 24://上传用户信息传输装置操作信息(共8个字节,信息体2个字节,时间6个字节)
                UserOperate userOperate = new UserOperate();
                int[] operates = Convert.toBinaryString(buf[++k]);
                userOperate.setReset(operates[7]);
                userOperate.setNoisedamping(operates[6]);
                userOperate.setManualAlarm(operates[5]);
                userOperate.setPoliceExterminate(operates[4]);
                userOperate.setSelfCheck(operates[3]);
                userOperate.setInspectTheSentries(operates[2]);
                userOperate.setTest(operates[1]);
                userOperate.setObligate(operates[0]);
                userOperate.setOperator(buf[++k]);
                //解析时间
                String userOperateTime = InfoType.getInfoType().byteToDate(InfoType.getInfoType().establishTimeByte
                        (buf,6,
                        k));
                userOperate.setTime(userOperateTime);
                //添加到Info
                List<UserOperate> userOperateList = new ArrayList<>();
                userOperateList.add(userOperate);
                infos.setUpUserOperates(userOperateList);

                break;
            case 25://上传用户信息传输装置软件版本(共8个字节,信息体2个字节,时间6个字节)
                UserSoftware userSoftware = new UserSoftware();
                userSoftware.setMainVersion(buf[++k]);
                userSoftware.setSecondary(buf[++k]);
                //解析时间
                String userSoftwareTime = InfoType.getInfoType().byteToDate(InfoType.getInfoType().establishTimeByte
                        (buf,6,
                                k));
                userSoftware.setTime(userSoftwareTime);
                //添加到Info
                List<UserSoftware> userSoftwareList = new ArrayList<>();
                userSoftwareList.add(userSoftware);
                infos.setUpUserSoftwares(userSoftwareList);

                break;
            case 26://上传用户信息传输装置配置情况(共n+7个字节,信息体n+1个字节,时间6个字节)
                UserConfigure userConfigure = new UserConfigure();
                int userExplanationLen = buf[++k];
                userConfigure.setExplanationLen(userExplanationLen);
                userConfigure.setExplanation(Utilty.parseByte2HexStr(InfoType.getInfoType().establishTimeByte(buf,
                        userExplanationLen,k)));
                //解析时间
                String userConfigureTime = InfoType.getInfoType().byteToDate(InfoType.getInfoType().establishTimeByte
                        (buf,6,
                                k));
                userConfigure.setTime(userConfigureTime);
                //添加到infos中
                List<UserConfigure> userConfigureList = new ArrayList<>();
                userConfigureList.add(userConfigure);
                infos.setUpUserConfigures(userConfigureList);


                break;
            case 28://上传用户信息传输装置系统时间(上行)
                String userSystemTime = InfoType.getInfoType().byteToDate(InfoType.getInfoType().establishTimeByte
                        (buf,6,k));
                System.out.println("上传用户信息传输装置系统时间(上行):" + userSystemTime);
                break;
        }
        //解析完成时让k值回到初始值,否则下次调用是会数组越界(这就是使用static的坏处啊(-_-`),猛男落泪)
        //k = -1;
    }

    /**
     * 将byte数组反转再转成string
     * @param buf 时间byte数组
     * @return String
     */
    private String byteToDate(byte[] buf){
        //System.out.println("时间数组:"+Utilty.parseByte2HexStr(buf));
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
     * @param len 长度
     * @param k 下标
     * @return
     */
    public byte[] establishTimeByte(byte[] buf,int len,int k){
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
     * @param k 下标
     * @return低字节数组
     */
    public byte[] toLowByte(byte[] buf,int length,int k){
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
    public byte[] toLowOut(byte[] bufs){
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
