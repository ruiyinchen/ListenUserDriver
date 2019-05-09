package com.jugan.analysis;

import com.jugan.entity.Info;
import com.jugan.tools.Utilty;

/**
 * 解析数据类
 * @Author CL
 * @Date 2019/4/3-9:43
 */
public class AnalysisData {

    private int k = 0;//数组下标
    private static AnalysisData info = new AnalysisData();

    public static AnalysisData getInfo() {
        return info;
    }
    /**
     * 解析数据
     * @param buf byte数组
     */
    public Info analysis(byte[] buf){
        System.out.print("数组:");
        //打印接收的信息
        for (int i = 0;i < buf.length;i++)
            System.out.print(buf[i] + " ");
        System.out.println();
        //实例化对象
        Info infos = new Info();
        /*头信息*/
        int head0 = buf[k];
        int head1 = buf[++k];
        infos.setHead0(head0);
        infos.setHead1(head1);

        /*业务流水号*/
        byte[] serial = new byte[2];
        for (int i = 0;i < serial.length;i++)
            serial[i] = buf[++k];
        int serialNumber = Integer.parseInt(Utilty.parseByte2HexStr(serial),16);
        infos.setSerialNumber(serialNumber);

        /*协议版本*/
        int systemProtcolVersion = buf[++k];
        int userProtcolVersion = buf[++k];
        infos.setSystemProtcolVersion(systemProtcolVersion);
        infos.setUserProtcolVersion(userProtcolVersion);

        /*时间*/
        byte[] times = new byte[6];
        for (int i = 0;i < times.length;i++)
            times[i] = buf[++k];
        StringBuffer sb = new StringBuffer();
        for (int i = times.length-1;i>=0;i--){
            String str = String.valueOf(Integer.parseInt(Utilty.parseByte2HexStr(new byte[]{times[i]}),16));
            if (str.length() == 1)
                str = "0"+ str;
            sb.append(str);
        }
        String time = sb.toString();
        //System.out.println(time);
        infos.setTime(time);
        /*源地址*/
        byte[] source = new byte[6];
        for (int i = 0;i < source.length;i++)
            source[i] = buf[++k];
        String souceAddress = Utilty.parseByte2HexStr(source);
        //System.out.println(souceAddress);
        infos.setSourceAddress(souceAddress);
        /*目的地址*/
        byte[] destination = new byte[6];
        for (int i = 0;i < destination.length;i++)
            destination[i] = buf[++k];
        String destinationAddress = Utilty.parseByte2HexStr(destination);
        //System.out.println(destinationAddress);
        infos.setDestinationAddress(destinationAddress);
        /*应用数据单元长度*/
        byte[] datasLength = new byte[2];
        int h = 1;
        for (int i = datasLength.length;i > 0;i--) {
            datasLength[h] = buf[++k];
            h -= 1;
        }
        int dataLength = Integer.parseInt(Utilty.parseByte2HexStr(datasLength),16);
        //System.out.println("\ndataLength:" + dataLength);
        infos.setDataLength(dataLength);

        /*命令*/
        int command = buf[++k];
        infos.setCommand(command);

        /*应用数据单元*/
        int infoType = buf[++k];//数据单元类型
        infos.setInfoType(infoType);
        int infoNum= buf[++k];//数据单元信息对象数目
        infos.setInfoNum(infoNum);
        //System.out.println("infoNum:" + infoNum);
        int infoLength = AnalysisData.getLength(infoType);
        /*信息体*/
        int kk = -1;
        for (int j = 0;j < infoNum;j++){//根据信息对象数目循环进行解析
            byte[] info = new byte[infoLength];
            for (int i = 0;i < info.length;i++)
                info[i] = buf[++kk];
            //根据数据单元类型分别进行解析
            InfoType.infoType(infoType,info,infos);
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
        k = 0;//初始化k
        return infos;
    }

    /**
     * 根据数据类型得到相应的数据长度
     * @param infoType 数据单元类型
     * @return
     */
    public static int getLength(int infoType){
        int num = 0;
        switch (infoType){
            case 1:
                num = 10;
                break;
            case 2:
                num = 46;
                break;
            case 21:
                num = 7;
                break;
            case 28:
                num = 6;
                break;
        }
        return num;
    }
}
