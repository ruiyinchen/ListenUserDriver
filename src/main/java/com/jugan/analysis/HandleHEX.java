package com.jugan.analysis;


import com.jugan.entity.util.ArraryIndex;
import com.jugan.tools.ProjectUtil;
import com.jugan.tools.Utilty;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理粘包,丢包等问题
 * @Author CL
 * @Date 2019/6/4-9:20
 */
public class HandleHEX {


    private static HandleHEX handleHEX = new HandleHEX();

    public static HandleHEX toHandle() { return handleHEX; }

    /**
     * 处理粘包
     * @param buf 原数组
     * @return 处理后的数组
     */
    public byte[] toArray(byte[] buf){


        //原数组长度
        int bufLen = buf.length;
        /**
         * 1.
         * 找到开始符 40 40
         * 遍历整个数组查询开始符
         * 找到时记录第一个40下标index
         */
        List<ArraryIndex> indexList = new ArrayList<>();
        for (int i = 0;i < bufLen;i++){
            if ((i+1) >= bufLen) break;
            if (buf[i] == buf[i+1] && buf[i] == 64 && buf[i+1] == 64){
                ArraryIndex index = new ArraryIndex();
                index.setIndex(i);
                indexList.add(index);
            }
        }

        /**
         * 2.
         * 创建新数组A存储从index之后的数组
         * 遍历新数组获得"类型标志"和"信息对象数目"
         * "类型标志"为6、66、26、86时,获得其说明长度
         * 计算数据包长度,并用新数组B存储
         * 判断最后两个字节是否是23 23
         * 如果是再校验和,不是即舍弃
         * 校验成功返回
         * 校验失败则使用数组A继续第一步第二步操作
         */

        for (ArraryIndex arraryIndex : indexList){
            int index = arraryIndex.getIndex();
            //System.out.println("index:" + index);
            //创建新数组
            byte[] newByte = new byte[bufLen-index];
            for (int i = 0;i < newByte.length;i++){
                newByte[i] = buf[index];
                index++;
            }
            int infoType = newByte[27];//数据单元类型标志
            int infoNum = newByte[28];//数据单元信息对象数目
            //获得数据类型单个对象的长度，已对"类型标志"为6、66、26、86长度处理
            int infoLength = ProjectUtil.getUtil().getLength(infoType,newByte);
            int totalLength = 32 + (infoNum * infoLength);//计算数据包长度
            //System.out.println("totalLength:" + totalLength);
            byte[] bytes = new byte[totalLength];
            for (int i = 0;i < bytes.length; i++){ bytes[i] = newByte[i]; }
            //判断最后两个字节是否是23 23
            if (bytes[totalLength-1] == bytes[totalLength-2] && bytes[totalLength-1] == 35){
                //计算校验和判断该数据是否正确
                if (calcCRC(bytes)){
                    return bytes;
                }
            }
        }

        return null;
    }

    public static byte[] buildSystemTime(){
        byte[] buf = {(byte)0x40,(byte)0x40,(byte)0x23,(byte)0x40,(byte)0x40,(byte)0xA2,(byte)0x00,(byte)0x01,(byte)0x01,(byte)0x3B,(byte)0x24,(byte)0x0A,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0xEE,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0xE9,(byte)0x03,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x08,(byte)0x00,(byte)0x02,(byte)0x08,(byte)0x02,(byte)0x23,(byte)0x1B,(byte)0x0B,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0x23,(byte)0x1B,(byte)0x0B,(byte)0x1E,(byte)0x05,(byte)0x13,(byte)0x2f,(byte)0x23,(byte)0x23};
        //System.out.println(buf.length);
        return buf;
    }


    public static void main(String[] args) {
        System.out.println(Utilty.parseByte2HexStr(HandleHEX.toHandle().toArray(HandleHEX.buildSystemTime())));
    }


    /**
     * 计算校验和
     * @param buf 原数组
     * @return true为计算正确
     */
    public boolean calcCRC(byte[] buf){
        //获得检验的数组
        byte[] newCrcs = new byte[buf.length - 5];//数组长度为总长度减去头和尾四个字节再减去校验和一个字节
        int num = 1;
        for (int i = 0;i < newCrcs.length;i++)
            newCrcs[i] = buf[++num];
        //获得该byte数组的校验和
        int ss = Utilty.sumCheck(newCrcs);
        //获取原数组的校验和
        byte[] crcs = {buf[buf.length-3]};
        int crc = Integer.parseInt(Utilty.parseByte2HexStr(crcs),16);
        System.out.println("原数组数据计算后的校验和:"+ss+"\t\t原数组提供的校验和:"+crc);
        //校验和进行比较,结果不一致则不解析数据
        if (ss == crc ) return true;
        return false;
    }
}
