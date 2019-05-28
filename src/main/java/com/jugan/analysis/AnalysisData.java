package com.jugan.analysis;

import com.jugan.entity.Info;
import com.jugan.tools.Utilty;

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
        int kk = -1;
        for (int i =0;i < infoNum;i++){//根据信息对象数目循环进行解析
            byte[] newByte = new byte[infoLength];
            for (int j = 0;j < newByte.length;j++){
                newByte[j] = dataInfo[++kk];
            }
            //System.out.println(Utilty.parseByte2HexStr(newByte));
            //根据数据单元类型分别进行解析
            InfoType.infoType(infoType,newByte,infos);
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
