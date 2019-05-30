package com.jugan.analysis;

import com.jugan.TestDemo;
import com.jugan.entity.Info;
import com.jugan.entity.type.*;
import com.jugan.tools.Utilty;

import java.util.ArrayList;
import java.util.List;

/**
 * 解析数据类
 * @Author CL
 * @Date 2019/4/3-9:43
 */
public class AnalysisData {


    private static AnalysisData info = new AnalysisData();

    public static AnalysisData getInfo() {
        return info;
    }
    /**
     * 解析数据
     * @param buf byte数组
     */
    public Info analysis(byte[] buf){
        int k = -1;//数组下标
       /* System.out.print("数组:");
        //打印接收的信息
        for (int i = 0;i < buf.length;i++)
            System.out.print(buf[i] + " ");
        System.out.println();*/
        //实例化对象
        Info infos = new Info();
        /*头信息*/
        int head0 = buf[++k];//下标为0
        int head1 = buf[++k];//下标为1
        infos.setHead0(head0);
        infos.setHead1(head1);

        /*业务流水号*/
        byte[] serial = new byte[2];
        for (int i = 0;i < serial.length;i++)
            serial[i] = buf[++k];
        int serialNumber = Integer.parseInt(Utilty.parseByte2HexStr(serial),16);
        infos.setSerialNumber(serialNumber);

        /*协议版本*/
        int systemProtcolVersion = buf[++k];//下标为4
        int userProtcolVersion = buf[++k];//下标为5
        infos.setSystemProtcolVersion(systemProtcolVersion);
        infos.setUserProtcolVersion(userProtcolVersion);

        //System.out.println("serialNumber:"+serialNumber+"\t\tsystemProtcolVersion:"+systemProtcolVersion+"\t" +
               // "\tuserProtcolVersion:"+userProtcolVersion);
        /*时间*/
        byte[] times = new byte[6];
        for (int i = 0;i < times.length;i++)
            times[i] = buf[++k];//下标6-->11
        StringBuffer sb = new StringBuffer();
        for (int i = times.length-1;i>=0;i--){
            String str = String.valueOf(Integer.parseInt(Utilty.parseByte2HexStr(new byte[]{times[i]}),16));
            if (str.length() == 1)
                str = "0"+ str;
            sb.append(str);
        }
        String time = sb.toString();
        infos.setTime(time);

        //System.out.println("时间:"+time);

        /*源地址*/
        byte[] source = new byte[6];
        for (int i = 0;i < source.length;i++)
            source[i] = buf[++k];//下标12-->17
        String souceAddress = Utilty.parseByte2HexStr(source);
       // System.out.println("源地址:"+souceAddress);
        infos.setSourceAddress(souceAddress);

        /*以下控制单元部分*/
        /*目的地址*/
        byte[] destination = new byte[6];
        for (int i = 0;i < destination.length;i++)
            destination[i] = buf[++k];//下标17-->23
        String destinationAddress = Utilty.parseByte2HexStr(destination);
        //System.out.println("源地址:"+souceAddress+"\t\t目的地址"+destinationAddress);
        infos.setDestinationAddress(destinationAddress);
        /*应用数据单元长度*/
        byte[] datasLength = new byte[2];
        int h = 1;
        for (int i = datasLength.length;i > 0;i--) {
            datasLength[h] = buf[++k];
            h -= 1;
        }
        int dataLength = Integer.parseInt(Utilty.parseByte2HexStr(datasLength),16);
        //System.out.println("应用数据单元长度:" + dataLength);
        infos.setDataLength(dataLength);
        /*命令*/
        int command = buf[++k];
        infos.setCommand(command);
        //System.out.println("应用数据单元长度:" + dataLength+"\t\tcommand:"+command);
        /*以上控制单元部分*/

        /*应用数据单元*/
        int infoType = buf[++k];//数据单元类型
        infos.setInfoType(infoType);
        int infoNum= buf[++k];//数据单元信息对象数目
        infos.setInfoNum(infoNum);
        //System.out.println("数据单元类型"+infoType+"\t\t数据单元信息对象数目:" + infoNum);
        int infoLength = AnalysisData.getInfo().getLength(infoType);//获得数据类型单个对象的长度
        //System.out.println("数据类型单个对象的长度:" + infoLength);

       // System.out.println(k);
        byte[] dataInfo = new byte[buf.length - 32];//创建应用数据单元信息体总数组
        for (int i =0;i <buf.length-32;i++)
            dataInfo[i] = buf[++k];//k=28...
        //System.out.println(Utilty.parseByte2HexStr(dataInfo));
        /*信息体*/
        switch (infoType){
            case 1://上传建筑消防设施系统状态(共占10个字节,信息体占4个字节,时间占6个字节)
                List<BuildSystem> buildSystems = Resolve.getResolve().buildSystem(infoNum,infoLength,dataInfo);
                infos.setBuildSystems(buildSystems);
                break;
            case 2://上传建筑消防设施部件运行状态(共占46个字节,信息体占40个字节,时间占6个字节)
                List<BuildPartsRun> buildPartList = Resolve.getResolve().buildPartsRun(infoNum,infoLength,dataInfo);
                infos.setBuildPartRuns(buildPartList);
                break;
            case 3://上传建筑消防设施部件模拟量值(共16 个字节,信息体 10个字节,时间6个字节)
                List<BuildPartsAnalog> upAnalogList = Resolve.getResolve().buildPartsAnalog(infoNum,infoLength,dataInfo);
                infos.setBuildPartsAnalogs(upAnalogList);
                break;
            case 4://上传建筑消防设施操作信息(共10个字节,信息体4个字节,时间6个字节)
                List<BuildOperate> operateList = Resolve.getResolve().buildOperate(infoNum,infoLength,dataInfo);
                infos.setBuildOperates(operateList);
                break;
            case 5://上传建筑消防设施软件版本(共10个字节,信息体4个字节,时间6个字节)
                List<BuildSoftware> softwareList = Resolve.getResolve().buildSoftware(infoNum,infoLength,dataInfo);
                infos.setBuildSoftwares(softwareList);
                break;
            case 6://上传建筑消防设施系统配置情况(共n+9,信息体n+3个字节,时间6个字节)
                List<BuildSystemConfigure> systemConfigureList = Resolve.getResolve().buildSystemConfigure(infoNum,infoLength,dataInfo);
                infos.setBuildSystemConfigures(systemConfigureList);
                break;
            case 7://上传建筑消防设施部件配置情况(共44个字节,信息体38个字节,时间6个字节)
                List<BuildPartsConfigure> partsConfigureList = Resolve.getResolve().buildPartsConfigure(infoNum,infoLength,dataInfo);
                infos.setBuildPartsConfigures(partsConfigureList);
                break;
            case 8://上传建筑消防设施系统时间(时间6个字节)
                List<BuildSystemTime> systemTimeList = Resolve.getResolve().buildSystemTime(infoNum,infoLength,dataInfo);
                infos.setBuildSystemTimes(systemTimeList);
                break;
            case 21://上传用户信息传输装置运行状态(共7个字节,信息体1个字节,时间6个字节)
                List<UserRun> userFuns = Resolve.getResolve().userFun(infoNum,infoLength,dataInfo);
                infos.setUserRuns(userFuns);
                break;
            case 24://上传用户信息传输装置操作信息(共8个字节,信息体2个字节,时间6个字节)
                List<UserOperate> userOperateList = Resolve.getResolve().userOperate(infoNum,infoLength,dataInfo);
                infos.setUserOperates(userOperateList);
                break;
            case 25://上传用户信息传输装置软件版本(共8个字节,信息体2个字节,时间6个字节)
                List<UserSoftware> userSoftwareList = Resolve.getResolve().userSoftware(infoNum,infoLength,dataInfo);
                infos.setUserSoftwares(userSoftwareList);
                break;
            case 26://上传用户信息传输装置配置情况(共n+7个字节,信息体n+1个字节,时间6个字节)
                List<UserConfigure> userConfigureList = Resolve.getResolve().userConfigure(infoNum,infoLength, dataInfo);
                infos.setUserConfigures(userConfigureList);
                break;
            case 28://上传用户信息传输装置系统时间(共6个字节,时间6个字节)
                List<UserSystemTime> userSystemTimeList = Resolve.getResolve().userSystemTime(infoNum,infoLength,dataInfo);
                infos.setUserSystemTimes(userSystemTimeList);
                break;

                /*下行部分*/
            case 61://读建筑消防设施系统状态(共占10个字节,信息体占4个字节,时间占6个字节)
                List<BuildSystem> downBuildSystems = Resolve.getResolve().buildSystem(infoNum,infoLength,dataInfo);
                infos.setBuildSystems(downBuildSystems);
                break;
            case 62://读建筑消防设施部件运行状态(共占46个字节,信息体占40个字节,时间占6个字节)
                List<BuildPartsRun> downBuildPartList = Resolve.getResolve().buildPartsRun(infoNum,infoLength,dataInfo);
                infos.setBuildPartRuns(downBuildPartList);
                break;
            case 63://读建筑消防设施部件模拟量值(共16 个字节,信息体 10个字节,时间6个字节)
                List<BuildPartsAnalog> downAnalogList = Resolve.getResolve().buildPartsAnalog(infoNum,infoLength,dataInfo);
                infos.setBuildPartsAnalogs(downAnalogList);
                break;
            case 64://读建筑消防设施操作信息(共10个字节,信息体4个字节,时间6个字节)
                List<BuildOperate> downOperateList = Resolve.getResolve().buildOperate(infoNum,infoLength,dataInfo);
                infos.setBuildOperates(downOperateList);
                break;
            case 65://读建筑消防设施软件版本(共10个字节,信息体4个字节,时间6个字节)
                List<BuildSoftware> downSoftwareList = Resolve.getResolve().buildSoftware(infoNum,infoLength,dataInfo);
                infos.setBuildSoftwares(downSoftwareList);
                break;
            case 66://读建筑消防设施系统配置情况(共n+9,信息体n+3个字节,时间6个字节)
                List<BuildSystemConfigure> downSystemConfigureList = Resolve.getResolve().buildSystemConfigure(infoNum,infoLength,dataInfo);
                infos.setBuildSystemConfigures(downSystemConfigureList);
                break;
            case 67://读建筑消防设施部件配置情况(共44个字节,信息体38个字节,时间6个字节)
                List<BuildPartsConfigure> downPartsConfigureList = Resolve.getResolve().buildPartsConfigure(infoNum,infoLength,dataInfo);
                infos.setBuildPartsConfigures(downPartsConfigureList);
                break;
            case 68://读建筑消防设施系统时间(时间6个字节)
                List<BuildSystemTime> downSystemTimeList = Resolve.getResolve().buildSystemTime(infoNum,infoLength,dataInfo);
                infos.setBuildSystemTimes(downSystemTimeList);
                break;
            case 81://读用户信息传输装置运行状态(共7个字节,信息体1个字节,时间6个字节)
                List<UserRun> downUserFuns = Resolve.getResolve().userFun(infoNum,infoLength,dataInfo);
                infos.setUserRuns(downUserFuns);
                break;
            case 84://读用户信息传输装置操作信息(共8个字节,信息体2个字节,时间6个字节)
                List<UserOperate> downUserOperateList = Resolve.getResolve().userOperate(infoNum,infoLength,dataInfo);
                infos.setUserOperates(downUserOperateList);
                break;
            case 85://读用户信息传输装置软件版本(共8个字节,信息体2个字节,时间6个字节)
                List<UserSoftware> downUserSoftwareList = Resolve.getResolve().userSoftware(infoNum,infoLength,dataInfo);
                infos.setUserSoftwares(downUserSoftwareList);
                break;
            case 86://读用户信息传输装置配置情况(共n+7个字节,信息体n+1个字节,时间6个字节)
                List<UserConfigure> downUserConfigureList = Resolve.getResolve().userConfigure(infoNum,infoLength, dataInfo);
                infos.setUserConfigures(downUserConfigureList);
                break;
            case 88://读用户信息传输装置系统时间(共6个字节,时间6个字节)
                List<UserSystemTime> downUserSystemTimeList = Resolve.getResolve().userSystemTime(infoNum,infoLength,dataInfo);
                infos.setUserSystemTimes(downUserSystemTimeList);
                break;
            case 89://初始化用户信息传输装置(共7个字节,信息体1个字节,时间6个字节)
                List<UserInit> userInitList = Resolve.getResolve().userInit(infoNum,infoLength,dataInfo);
                infos.setUserInits(userInitList);
                break;
            case 90://同步用户信息传输装置时钟(共6个字节,时间6个字节)
                List<UserSyncClock> userSyncClockList = Resolve.getResolve().clock(infoNum,infoLength,dataInfo);
                infos.setUserSyncClocks(userSyncClockList);
                break;
            case 91://查岗命令(共7个字节,信息体1个字节,时间6个字节)
                List<InspectTheSentriesCommand> commandList = Resolve.getResolve().command(infoNum,infoLength,dataInfo);
                infos.setSentriescommands(commandList);
                break;
            default:
                break;
        }


        /*校验和*/
        byte[] crcs = {buf[buf.length-3]};
        int crc = Integer.parseInt(Utilty.parseByte2HexStr(crcs),16);
        infos.setCrc(crc);

        /*结束符*/
        int end0 = buf[buf.length-2];
        int end1 = buf[buf.length-1];
        infos.setEnd0(end0);
        infos.setEnd1(end1);
        return infos;
    }

    /**
     * 根据数据类型得到相应的数据长度
     * @param infoType 数据单元类型
     * @return
     */
    public int getLength(int infoType){
        int num = 0;
        switch (infoType){
            case 1://上传建筑消防设施系统状态(共占10个字节,信息体占4个字节,时间占6个字节)
                num = 10;
                break;
            case 2://上传建筑消防设施部件运行状态(共占46个字节,信息体占40个字节,时间占6个字节)
                num = 46;
                break;
            case 3://上传建筑消防设施部件模拟量值(共16 个字节,信息体 10个字节,时间6个字节)
                num = 16;
                break;
            case 4://上传建筑消防设施操作信息(共10个字节,信息体4个字节,时间6个字节)
                num = 10;
                break;
            case 5://上传建筑消防设施软件版本(共10个字节,信息体4个字节,时间6个字节)
                num = 10;
                break;
            case 6://上传建筑消防设施系统配置情况(共n+9,信息体n+3个字节,时间6个字节)
                num = 46;
                break;
            case 7://上传建筑消防设施部件配置情况(共44个字节,信息体38个字节,时间6个字节)
                num = 44;
                break;
            case 8://上传建筑消防设施系统时间(时间6个字节)
                num = 6;
                break;
            case 21://上传用户信息传输装置运行状态(共7个字节,信息体1个字节,时间6个字节)
                num = 7;
                break;
            case 24://上传用户信息传输装置操作信息(共8个字节,信息体2个字节,时间6个字节)
                num = 8;
                break;
            case 25://上传用户信息传输装置软件版本(共8个字节,信息体2个字节,时间6个字节)
                num = 8;
                break;
            case 26://上传用户信息传输装置配置情况(共n+7个字节,信息体n+1个字节,时间6个字节)
                num = 7;
                break;
            case 28://上传用户信息传输装置系统时间(共6个字节,时间6个字节)
                num = 6;
                break;
            case 61://读建筑消防设施系统状态(共占10个字节,信息体占4个字节,时间占6个字节)
                num = 10;
                break;
            case 62://读建筑消防设施部件运行状态(共占46个字节,信息体占40个字节,时间占6个字节)
                num = 46;
                break;
            case 63://读建筑消防设施部件模拟量值(共16 个字节,信息体 10个字节,时间6个字节)
                num = 16;
                break;
            case 64://读建筑消防设施操作信息(共10个字节,信息体4个字节,时间6个字节)
                num = 10;
                break;
            case 65://读建筑消防设施软件版本(共10个字节,信息体4个字节,时间6个字节)
                num = 10;
                break;
            case 66://读建筑消防设施系统配置情况(共n+9,信息体n+3个字节,时间6个字节)
                num = 9;//暂定为9
                break;
            case 67://读建筑消防设施部件配置情况(共44个字节,信息体38个字节,时间6个字节)
                num = 44;
                break;
            case 68://读建筑消防设施系统时间(时间6个字节)
                num = 6;
                break;
            case 81://读用户信息传输装置运行状态(共7个字节,信息体1个字节,时间6个字节)
                num = 7;
                break;
            case 84://读用户信息传输装置操作信息(共8个字节,信息体2个字节,时间6个字节)
                num = 8;
                break;
            case 85://读用户信息传输装置软件版本(共8个字节,信息体2个字节,时间6个字节)
                num = 8;
                break;
            case 86://读用户信息传输装置配置情况(共n+7个字节,信息体n+1个字节,时间6个字节)
                num = 7;//暂定为7
                break;
            case 88://读用户信息传输装置系统时间(共6个字节,时间6个字节)
                num = 6;
                break;
            case 89://初始化用户信息传输装置(共7个字节,信息体1个字节,时间6个字节)
                num =7;
                break;
            case 90://同步用户信息传输装置时钟(共6个字节,时间6个字节)
                num = 6;
                break;
            case 91://查岗命令(共7个字节,信息体1个字节,时间6个字节)
                num = 7;
                break;
            default:
                num = 0;//暂定为0
                break;
        }
        return num;
    }
}
