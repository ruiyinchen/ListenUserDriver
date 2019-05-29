package com.jugan.analysis;

import com.jugan.entity.type.*;
import com.jugan.tools.Convert;
import com.jugan.tools.Utilty;

import java.util.ArrayList;
import java.util.List;

/**
 * 类型标志通道解析
 *
 * @Author CL
 * @Date 2019/5/28-17:41
 */
public class Resolve {
    private static Resolve resolve = new Resolve();

    public static Resolve getResolve() {
        return resolve;
    }

    /**
     * 循环思路:
     * 拆分总数组,获得多个单个对象数组且长度一致
     * 依次对单个对象数组进行解析
     * 如总数组为0001020304050607080910 对象数目为2 长度为5
     * 单个对象数分别为0001020304   0506070809
     */

    /**
     * 建筑消防设施系统状态<br/>
     * 单个数据共占10个字节,信息体占4个字节,时间占6个字节
     * @param infoNum 数据单元信息对象数目
     * @param infoLength 数据类型单个对象的长度
     * @param dataInfo 应用数据单元信息体总数组
     * @return List<BuildSystem>集合
     */
    public List<BuildSystem> buildSystem(int infoNum,int infoLength,byte[] dataInfo){
        int kk = -1;
        List<BuildSystem> buildSystems = new ArrayList<>();
        for (int i =0;i < infoNum;i++) {//根据信息对象数目循环进行解析
            byte[] newByte = new byte[infoLength];
            for (int j = 0; j < newByte.length; j++) {
                newByte[j] = dataInfo[++kk];
            }
            int k = -1;
            //封装BuildSystem对象中
            BuildSystem system = new BuildSystem();
            system.setSystemType(newByte[++k]);//系统类型标志
            system.setSystemAddress(newByte[++k]);//系统地址
            byte[] systemState = Utilty.toLowByte(newByte,2,k);//系统状态
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
            String buildSystemTime = Utilty.byteToDate(Utilty.establishTimeByte(newByte,6,k));
            system.setTime(buildSystemTime);
            System.out.println("上传建筑消防设施系统状态(上行):" + buildSystemTime);
            //添加到集合中
            buildSystems.add(system);
        }
        return buildSystems;
    }

    /**
     * 建筑消防设施部件运行状态<br/>
     * 单个数据共占46个字节,信息体占40个字节,时间占6个字节
     * @param infoNum 数据单元信息对象数目
     * @param infoLength 数据类型单个对象的长度
     * @param dataInfo 应用数据单元信息体总数组
     * @return List<BuildPartsRun>集合
     */
    public List<BuildPartsRun> buildPartsRun(int infoNum,int infoLength,byte[] dataInfo){
        int kk = -1;
        List<BuildPartsRun> buildPartList =new ArrayList<>();
        for (int i =0;i < infoNum;i++){//根据信息对象数目循环进行解析
            byte[] newByte = new byte[infoLength];
            for (int j = 0;j < newByte.length;j++){
                newByte[j] = dataInfo[++kk];
            }
            int k = -1;
            //封装BuildPartsRun对象
            BuildPartsRun parts = new BuildPartsRun();
            parts.setSystemType(newByte[++k]);//系统类型标志
            parts.setSystemAddress(newByte[++k]);//系统地址
            parts.setPartsType(newByte[++k]);//部件类型
            String partsAddress = Utilty.parseByte2HexStr(Utilty.establishTimeByte(newByte,4,k));//部件地址
            parts.setPartsAddress(partsAddress);
            byte[] partsState = Utilty.toLowByte(newByte,2,k);//部件状态
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
            byte[] partsExplains = Utilty.establishTimeByte(newByte,31,k);
            parts.setPartsExplain(Utilty.parseByte2HexStr(partsExplains));

            //解析时间
            String buildPartsTime = Utilty.byteToDate(Utilty.establishTimeByte(newByte,6,k));
            parts.setTime(buildPartsTime);
            //添加到集合中
            buildPartList.add(parts);

            System.out.println("传建筑消防设施部件运行状态"+buildPartsTime);
        }
        return buildPartList;
    }

    /**
     * 建筑消防设施部件模拟量值<br/>
     * 单个数据共16 个字节,信息体 10个字节,时间6个字节
     * @param infoNum 数据单元信息对象数目
     * @param infoLength 数据类型单个对象的长度
     * @param dataInfo 应用数据单元信息体总数组
     * @return List<BuildPartsAnalog>集合
     */
    public List<BuildPartsAnalog> buildPartsAnalog(int infoNum,int infoLength,byte[] dataInfo){
        int kk = -1;
        List<BuildPartsAnalog> upAnalogList = new ArrayList<>();
        for (int i =0;i < infoNum;i++) {//根据信息对象数目循环进行解析
            byte[] newByte = new byte[infoLength];
            for (int j = 0; j < newByte.length; j++) {
                newByte[j] = dataInfo[++kk];
            }
            int k = -1;
            //封装BuildPartsAnalog对象
            BuildPartsAnalog analog = new BuildPartsAnalog();
            analog.setSystemType(newByte[++k]);//系统状态
            analog.setSystemAddress(newByte[++k]);//系统地址
            analog.setPartsType(newByte[++k]);//部件类型
            //部件地址
            analog.setPartsAddress(Utilty.parseByte2HexStr(Utilty.establishTimeByte(newByte,4,k)));
            analog.setAnalogType(newByte[++k]);//模拟量类型
            //模拟量值<获得低字节数组>
            byte[] analogValues = Utilty.toLowOut(Utilty.establishTimeByte(newByte,2,k));
            //模拟量值<有符号>
            int analogValue = Utilty.num2Hex(Integer.parseInt(Utilty.parseByte2HexStr(analogValues),16),2);
            analog.setAnalogValues(analogValue);
            //解析时间
            String buildPartsAnalogTime = Utilty.byteToDate(Utilty.establishTimeByte(newByte,6,k));
            analog.setTime(buildPartsAnalogTime);
            //添加到集合中
            upAnalogList.add(analog);
        }
        return upAnalogList;
    }

    /**
     * 建筑消防设施操作信息<br/>
     * 单个数据共10个字节,信息体4个字节,时间6个字节
     * @param infoNum 数据单元信息对象数目
     * @param infoLength 数据类型单个对象的长度
     * @param dataInfo 应用数据单元信息体总数组
     * @return List<BuildOperate>集合
     */
    public List<BuildOperate> buildOperate(int infoNum,int infoLength,byte[] dataInfo){
        int kk = -1;
        List<BuildOperate> operateList = new ArrayList<>();
        for (int i =0;i < infoNum;i++) {//根据信息对象数目循环进行解析
            byte[] newByte = new byte[infoLength];
            for (int j = 0; j < newByte.length; j++) {
                newByte[j] = dataInfo[++kk];
            }
            int k = -1;
            //封装BuildOperate对象
            BuildOperate operate = new BuildOperate();
            operate.setSystemType(newByte[++k]);//系统类型
            operate.setSystemAddress(newByte[++k]);//系统地址
            //获得操作标志
            byte[] buildOperate = Utilty.toLowByte(newByte,1,k);
            int[] operateValues = Convert.toBinaryString(Integer.parseInt(Utilty.parseByte2HexStr(buildOperate)));
            operate.setReset(operateValues[7]);
            operate.setNoisedamping(operateValues[6]);
            operate.setManualAlarm(operateValues[5]);
            operate.setPoliceExterminate(operateValues[4]);
            operate.setSelfCheck(operateValues[3]);
            operate.setValidation(operateValues[2]);
            operate.setTest(operateValues[1]);
            operate.setObligate(operateValues[0]);
            operate.setOperator(newByte[++k]);
            //时间
            String buildOperateTime = Utilty.byteToDate(Utilty.establishTimeByte (newByte,6,k));
            operate.setTime(buildOperateTime);
            //添加到集合中
            operateList.add(operate);
        }

        return operateList;
    }

    /**
     * 建筑消防设施软件版本<br/>
     * 单个数据共10个字节,信息体4个字节,时间6个字节
     * @param infoNum 数据单元信息对象数目
     * @param infoLength 数据类型单个对象的长度
     * @param dataInfo 应用数据单元信息体总数组
     * @return List<UserRun>集合
     */
    public List<BuildSoftware> buildSoftware(int infoNum,int infoLength,byte[] dataInfo){
        int kk = -1;
        List<BuildSoftware> softwareList = new ArrayList<>();
        for (int i =0;i < infoNum;i++) {//根据信息对象数目循环进行解析
            byte[] newByte = new byte[infoLength];
            for (int j = 0; j < newByte.length; j++) {
                newByte[j] = dataInfo[++kk];
            }
            int k = -1;
            //封装BuildSoftware对象
            BuildSoftware software = new BuildSoftware();
            software.setSystemType(newByte[++k]);
            software.setSystemAddress(newByte[++k]);
            software.setMainVersion(newByte[++k]);
            software.setSecondary(newByte[++k]);
            String buildSoftwareTime = Utilty.byteToDate(Utilty.establishTimeByte(newByte,6,k));
            software.setTime(buildSoftwareTime);
            //添加到集合中
            softwareList.add(software);
        }
        return softwareList;

    }


    /**
     * 建筑消防设施系统配置情况<br/>
     * 单个数据共n+9,信息体n+3个字节,时间6个字节
     * @param infoNum 数据单元信息对象数目
     * @param infoLength 数据类型单个对象的长度
     * @param dataInfo 应用数据单元信息体总数组
     * @return List<BuildSystemConfigure>集合
     */
    public List<BuildSystemConfigure> buildSystemConfigure(int infoNum,int infoLength,byte[] dataInfo){
        int kk = -1;
        List<BuildSystemConfigure> systemConfigureList = new ArrayList<>();
        for (int i =0;i < infoNum;i++) {//根据信息对象数目循环进行解析
            byte[] newByte = new byte[infoLength];
            for (int j = 0; j < newByte.length; j++) {
                newByte[j] = dataInfo[++kk];
            }
            int k = -1;
            //封装BuildSystemConfigure对象
            BuildSystemConfigure systemConfigure = new BuildSystemConfigure();
            systemConfigure.setSystemType(newByte[++k]);
            systemConfigure.setSystemAddress(newByte[++k]);
            int explanation = newByte[++k];
            systemConfigure.setExplanationLen(explanation);
            systemConfigure.setExplanation(Utilty.parseByte2HexStr(Utilty.establishTimeByte(newByte,explanation,k)));
            String buildSystemConfigureTime = Utilty.byteToDate(Utilty.establishTimeByte(newByte,6,k));
            systemConfigure.setTime(buildSystemConfigureTime);
            //添加到集合中
            systemConfigureList.add(systemConfigure);
        }

        return systemConfigureList;

    }

    /**
     * 建筑消防设施部件配置情况<br/>
     * 单个数据共44个字节,信息体38个字节,时间6个字节
     * @param infoNum 数据单元信息对象数目
     * @param infoLength 数据类型单个对象的长度
     * @param dataInfo 应用数据单元信息体总数组
     * @return List<BuildPartsConfigure>集合
     */
    public List<BuildPartsConfigure> buildPartsConfigure(int infoNum,int infoLength,byte[] dataInfo){
        int kk = -1;
        List<BuildPartsConfigure> partsConfigureList = new ArrayList<>();
        for (int i =0;i < infoNum;i++) {//根据信息对象数目循环进行解析
            byte[] newByte = new byte[infoLength];
            for (int j = 0; j < newByte.length; j++) {
                newByte[j] = dataInfo[++kk];
            }
            int k = -1;
            //封装BuildPartsConfigure对象
            BuildPartsConfigure partsConfigure = new BuildPartsConfigure();
            partsConfigure.setSystemType(newByte[++k]);
            partsConfigure.setSystemAddress(newByte[++k]);
            partsConfigure.setPartsType(newByte[++k]);
            partsConfigure.setPartsAddress(Utilty.parseByte2HexStr(Utilty.toLowOut(Utilty.establishTimeByte(newByte,
                    4,k))));
            partsConfigure.setExplanation(Utilty.parseByte2HexStr(Utilty.establishTimeByte(newByte,31,k)));
            String buildPartsConfigureTime = Utilty.byteToDate(Utilty.establishTimeByte(newByte,6,k));
            partsConfigure.setTime(buildPartsConfigureTime);
            //添加到集合中
            partsConfigureList.add(partsConfigure);
        }
        return partsConfigureList;

    }

    /**
     * 建筑消防设施系统时间<br/>
     * 单个数据时间6个字节
     * @param infoNum 数据单元信息对象数目
     * @param infoLength 数据类型单个对象的长度
     * @param dataInfo 应用数据单元信息体总数组
     * @return List<BuildSystemTime>集合
     */
    public List<BuildSystemTime> buildSystemTime(int infoNum,int infoLength,byte[] dataInfo){
        int kk = -1;
        List<BuildSystemTime> systemTimeList = new ArrayList<>();
        for (int i =0;i < infoNum;i++) {//根据信息对象数目循环进行解析
            byte[] newByte = new byte[infoLength];
            for (int j = 0; j < newByte.length; j++) {
                newByte[j] = dataInfo[++kk];
            }
            //封装BuildSystemTime对象
            BuildSystemTime systemTime = new BuildSystemTime();
            String buildSystemTimes = Utilty.byteToDate(Utilty.establishTimeByte(newByte,6,-1));
            systemTime.setTime(buildSystemTimes);
            //添加到集合中
            systemTimeList.add(systemTime);
        }
        return systemTimeList;

    }

    /**
     * 用户信息传输装置运行状态<br/>
     * 单个数据共7个字节,信息体1个字节,时间6个字节
     * @param infoNum 数据单元信息对象数目
     * @param infoLength 数据类型单个对象的长度
     * @param dataInfo 应用数据单元信息体总数组
     * @return List<UserRun>集合
     */
    public List<UserRun> userFun(int infoNum,int infoLength,byte[] dataInfo){
        int kk = -1;
        List<UserRun> userFunctions = new ArrayList<>();
        for (int i =0;i < infoNum;i++){//根据信息对象数目循环进行解析
            byte[] newByte = new byte[infoLength];
            for (int j = 0;j < newByte.length;j++){
                newByte[j] = dataInfo[++kk];
            }
            int k = -1;
            //封装UserRun对象
            UserRun userFunction = new UserRun();
            int[] userFun = Convert.toBinaryString(newByte[++k]);
            userFunction.setReserve(userFun[0]);
            userFunction.setLine(userFun[1]);
            userFunction.setCommunication(userFun[2]);
            userFunction.setEps(userFun[3]);
            userFunction.setMps(userFun[4]);
            userFunction.setFault(userFun[5]);
            userFunction.setFireAlarm(userFun[6]);
            userFunction.setMonitor(userFun[7]);
            //解析时间
            String userFunTime = Utilty.byteToDate(Utilty.establishTimeByte(newByte,6,k));
            userFunction.setTime(userFunTime);
            //System.out.println("上传用户信息传输装置运行状态(上行):" + userFunTime);
            //添加到集合中
            userFunctions.add(userFunction);
        }
        return userFunctions;
    }

    /**
     * 用户信息传输装置操作信息<br/>
     * 单个数据共8个字节,信息体2个字节,时间6个字节
     * @param infoNum 数据单元信息对象数目
     * @param infoLength 数据类型单个对象的长度
     * @param dataInfo 应用数据单元信息体总数组
     * @return List<UserOperate>集合
     */
    public List<UserOperate> userOperate(int infoNum,int infoLength,byte[] dataInfo){
        int kk = -1;
        List<UserOperate> userOperateList = new ArrayList<>();
        for (int i =0;i < infoNum;i++){//根据信息对象数目循环进行解析
            byte[] newByte = new byte[infoLength];
            for (int j = 0;j < newByte.length;j++)
                newByte[j] = dataInfo[++kk];
            int k = -1;
            //封装UserOperate对象
            UserOperate userOperate = new UserOperate();
            int[] operates = Convert.toBinaryString(newByte[++k]);
            userOperate.setReset(operates[7]);
            userOperate.setNoisedamping(operates[6]);
            userOperate.setManualAlarm(operates[5]);
            userOperate.setPoliceExterminate(operates[4]);
            userOperate.setSelfCheck(operates[3]);
            userOperate.setInspectTheSentries(operates[2]);
            userOperate.setTest(operates[1]);
            userOperate.setObligate(operates[0]);
            userOperate.setOperator(newByte[++k]);
            //解析时间
            String userOperateTime = Utilty.byteToDate(Utilty.establishTimeByte(newByte,6,k));
            userOperate.setTime(userOperateTime);
            //添加到集合中
            userOperateList.add(userOperate);
        }
       return userOperateList;

    }



    /**
     * 用户信息传输装置软件版本<br/>
     * 单个数据共8个字节,信息体2个字节,时间6个字节
     * @param infoNum 数据单元信息对象数目
     * @param infoLength 数据类型单个对象的长度
     * @param dataInfo 应用数据单元信息体总数组
     * @return List<UserSoftware>集合
     */
    public List<UserSoftware> userSoftware(int infoNum,int infoLength,byte[] dataInfo){
        int kk = -1;
        List<UserSoftware> userSoftwareList = new ArrayList<>();
        for (int i =0;i < infoNum;i++) {//根据信息对象数目循环进行解析
            byte[] newByte = new byte[infoLength];
            for (int j = 0; j < newByte.length; j++)
                newByte[j] = dataInfo[++kk];
            int k = -1;
            //封装UserSoftware对象
            UserSoftware userSoftware = new UserSoftware();
            userSoftware.setMainVersion(newByte[++k]);
            userSoftware.setSecondary(newByte[++k]);
            //解析时间
            String userSoftwareTime = Utilty.byteToDate(Utilty.establishTimeByte(newByte, 6,k));
            userSoftware.setTime(userSoftwareTime);
            //添加到集合中
            userSoftwareList.add(userSoftware);
        }
        return userSoftwareList;

    }

    /**
     * 用户信息传输装置配置情况<br/>
     * 单个数据共n+7个字节,信息体n+1个字节,时间6个字节
     * @param infoNum 数据单元信息对象数目
     * @param infoLength 数据类型单个对象的长度
     * @param dataInfo 应用数据单元信息体总数组
     * @return List<UserConfigure>集合
     */
    public List<UserConfigure> userConfigure(int infoNum,int infoLength,byte[] dataInfo){
        int kk = -1;
        List<UserConfigure> userConfigureList = new ArrayList<>();
        for (int i =0;i < infoNum;i++) {//根据信息对象数目循环进行解析
            byte[] newByte = new byte[infoLength];
            for (int j = 0; j < newByte.length; j++)
                newByte[j] = dataInfo[++kk];
            int k = -1;
            //封装UserConfigure对象
            UserConfigure userConfigure = new UserConfigure();
            int userExplanationLen = newByte[++k];
            userConfigure.setExplanationLen(userExplanationLen);
            userConfigure.setExplanation(Utilty.parseByte2HexStr(Utilty.establishTimeByte(newByte,
                    userExplanationLen,k)));
            //解析时间
            String userConfigureTime = Utilty.byteToDate(Utilty.establishTimeByte(newByte, 6,k));
            userConfigure.setTime(userConfigureTime);
            //添加到集合中
            userConfigureList.add(userConfigure);
        }
        return userConfigureList;

    }


    /**
     * 用户信息传输装置系统时间<br/>
     * 单个数据共6个字节,时间6个字节
     * @param infoNum 数据单元信息对象数目
     * @param infoLength 数据类型单个对象的长度
     * @param dataInfo 应用数据单元信息体总数组
     * @return List<UserSystemTime>集合
     */
    public List<UserSystemTime> userSystemTime(int infoNum,int infoLength,byte[] dataInfo){
        int kk = -1;
        List<UserSystemTime> userSystemTimeList = new ArrayList<>();
        for (int i =0;i < infoNum;i++){//根据信息对象数目循环进行解析
            byte[] newByte = new byte[infoLength];
            for (int j = 0;j < newByte.length;j++){
                newByte[j] = dataInfo[++kk];
            }
            String userSystemTime = Utilty.byteToDate(Utilty.establishTimeByte(newByte,6,-1));
            UserSystemTime userTime = new UserSystemTime();
            userTime.setTime(userSystemTime);
            //System.out.println("上传用户信息传输装置系统时间(上行):" + userSystemTime);
            userSystemTimeList.add(userTime);
        }
        return userSystemTimeList;
    }

    /**
     * 初始化用户信息传输装置<br/>
     * 单个数据共7个字节,信息体1个字节,时间6个字节
     * @param infoNum 数据单元信息对象数目
     * @param infoLength 数据类型单个对象的长度
     * @param dataInfo 应用数据单元信息体总数组
     * @return List<UserInit>集合
     */
    public List<UserInit> userInit(int infoNum,int infoLength,byte[] dataInfo){
        int kk = -1;
        List<UserInit> userInitList = new ArrayList<>();
        for (int i =0;i < infoNum;i++) {//根据信息对象数目循环进行解析
            byte[] newByte = new byte[infoLength];
            for (int j = 0; j < newByte.length; j++) {
                newByte[j] = dataInfo[++kk];
            }
            int k = -1;
            //封装UserInit对象
            UserInit userInit = new UserInit();
            userInit.setObligate(newByte[++k]);//转十进制
            String userInitTime = Utilty.byteToDate(Utilty.establishTimeByte(newByte,6,k));
            userInit.setTime(userInitTime);
            //添加到集合中
            userInitList.add(userInit);
        }
        return userInitList;
    }
    /**
     * 查岗命令<br/>
     * 单个数据共7个字节,信息体1个字节,时间6个字节
     * @param infoNum 数据单元信息对象数目
     * @param infoLength 数据类型单个对象的长度
     * @param dataInfo 应用数据单元信息体总数组
     * @return List<InspectTheSentriesCommand>集合
     */
    public List<InspectTheSentriesCommand> command(int infoNum,int infoLength,byte[] dataInfo) {
        int kk = -1;
        List<InspectTheSentriesCommand> commandList = new ArrayList<>();
        for (int i = 0; i < infoNum; i++) {//根据信息对象数目循环进行解析
            byte[] newByte = new byte[infoLength];
            for (int j = 0; j < newByte.length; j++) {
                newByte[j] = dataInfo[++kk];
            }
            int k = -1;
            //封装InspectTheSentriesCommand对象
            InspectTheSentriesCommand command = new InspectTheSentriesCommand();
            command.setInspectTheSentries(newByte[++k]);
            String commandTime = Utilty.byteToDate(Utilty.establishTimeByte(newByte,6,k));
            command.setTime(commandTime);
            //添加到集合中
            commandList.add(command);
        }
        return commandList;
    }

    /**
     * 同步用户信息传输装置时钟<br/>
     * 单个数据共6个字节,时间6个字节
     * @param infoNum 数据单元信息对象数目
     * @param infoLength 数据类型单个对象的长度
     * @param dataInfo 应用数据单元信息体总数组
     * @return List<UserSyncClock>集合
     */
    public List<UserSyncClock> clock(int infoNum,int infoLength,byte[] dataInfo) {
        int kk = -1;
        List<UserSyncClock> userSyncClockList = new ArrayList<>();
        for (int i = 0; i < infoNum; i++) {//根据信息对象数目循环进行解析
            byte[] newByte = new byte[infoLength];
            for (int j = 0; j < newByte.length; j++) {
                newByte[j] = dataInfo[++kk];
            }
            UserSyncClock clock = new UserSyncClock();
            String clockTime = Utilty.byteToDate(Utilty.establishTimeByte(newByte,6,-1));
            clock.setTime(clockTime);
            userSyncClockList.add(clock);
        }
        return userSyncClockList;
    }
}
