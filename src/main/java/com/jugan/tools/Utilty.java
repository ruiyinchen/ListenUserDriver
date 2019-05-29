package com.jugan.tools;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 数据转换工具类
 */
public class Utilty {

    private static Utilty instance = new Utilty();

    public static Utilty getInstance() {
        return instance;
    }

    public static final int MIN_MID_VALUE = 1;

    public static final int MAX_MID_VALUE = 65535;


    /**
     * byte 转 int (????????????????)
     * @param b
     * @param start
     * @param length
     * @return
     */
    public int bytes2Int(byte[] b, int start, int length) {
        int sum = 0;
        int end = start + length;
        for (int k = start; k < end; k++) {
            int n = ((int) b[k]) & 0xff;
            n <<= (--length) * 8;
            sum += n;
        }
        return sum;
    }

    /**
     * 数值转正负数
     *
     * @param num 数值
     * @param len 长度
     * @return 值
     */
    public static int num2Hex(int num,int len) {
        if(((num>>(len*8-1)) & 0x01) == 0x01)
            return (num - (0x01<<(len*8)));
        return num;
    }

    /**
     * int转byte[]
     * 该方法将一个int类型的数据转换为byte[]形式，因为int为32bit，而byte为8bit所以在进行类型转换时，知会获取低8位，
     * 丢弃高24位。通过位移的方式，将32bit的数据转换成4个8bit的数据。注意 &0xff，在这当中，&0xff简单理解为一把剪刀，
     * 将想要获取的8位数据截取出来。
     * @param i 一个int数字
     * @return byte[]
     */
    public static byte[] int2ByteArray(int i) {
        byte[] result = new byte[4];
        result[0] = (byte) ((i >> 24) & 0xFF);
        result[1] = (byte) ((i >> 16) & 0xFF);
        result[2] = (byte) ((i >> 8) & 0xFF);
        result[3] = (byte) (i & 0xFF);
        return result;
    }

    /**
     * byte[]转int
     * 利用int2ByteArray方法，将一个int转为byte[]，但在解析时，需要将数据还原。同样使用移位的方式，将适当的位数进行还原，
     * 0xFF为16进制的数据，所以在其后每加上一位，就相当于二进制加上4位。同时，使用|=号拼接数据，将其还原成最终的int数据
     *
     * @param bytes byte类型数组
     * @return int数字
     */
    public static int bytes2Int(byte[] bytes) {
        int num = bytes[3] & 0xFF;
        num |= ((bytes[2] << 8) & 0xFF00);
        num |= ((bytes[1] << 16) & 0xFF0000);
        num |= ((bytes[0] << 24) & 0xFF0000);
        return num;
    }

  

    /**
     * int 转 byte 数组
     * @param value 数值
     * @param length 数组长度
     * @return
     */
    public byte[] int2Bytes(int value, int length) {
        byte[] b = new byte[length];
        for (int k = 0; k < length; k++) {
            b[length - k - 1] = (byte) ((value >> 8 * k) & 0xff);
        }
        return b;
    }

    /**
     * 数值转正负数
     * @param num 数值
     * @return 值
     */
    public static int num2Hex(int num){
        int ret = ((num & 0x8000) > 0) ? (num - 0x10000) : (num);
        return ret;
    }

    /**
     * 参数值是否在最大值和最小值之间(???mid??????)
     *
     * @param mId
     * @return
     */
    public boolean isValidofMid(int mId) {
        if (mId < MIN_MID_VALUE || mId > MAX_MID_VALUE) {
            return false;
        }
        return true;
    }


    /**
     * 字节转16进制
     * @param buf
     * @return String
     */
    public static String parseByte2HexStr(byte[] buf) {
        if (null == buf) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 16进制的字符串表示转成字节数组
     * @param hexString  16进制格式的字符串
     * @return 转换后的字节数组
     **/
    public static byte[] toByteArray(String hexString) {
        if (hexString.equals(""))
            throw new IllegalArgumentException("this hexString must not be empty");

        hexString = hexString.toLowerCase();
        final byte[] byteArray = new byte[hexString.length() / 2];
        int k = 0;
        for (int i = 0; i < byteArray.length; i++) {//因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
            byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
            byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
            byteArray[i] = (byte) (high << 4 | low);
            k += 2;
        }
        return byteArray;
    }

    public static String toStr(byte[] b){
        String ret = "";
        for(int i = 0, len = b.length; i < len; i ++) {
            String hex =  Integer.toHexString(b[i] & 0xff).toUpperCase();
            ret += hex.length()  == 1 ? "0" + hex : hex;
        }
        //System.out.println("\nret值:"+ret);
        return ret;
    }



    /**
     * int数组转byte数组
     * @param src 源int数组
     * offset 起始位置,一般为0
     * @return
     */
    public static byte[] intToBytes(int[] src){
        int offset = 0;
        byte[] values = new byte[src.length*4];
        for(int i = 0; i < src.length; i++ ){
            values[offset+3] = (byte)((src[i] >> 24) & 0xFF);
            values[offset+2] = (byte)((src[i] >> 16) & 0xFF);
            values[offset+1] = (byte)((src[i]  >> 8) & 0xFF);
            values[offset] = (byte)(src[i]  & 0xFF);
            offset+=4;
        }
        return values;
    }
    /**
     * 16进制转ASCII
     * @param hex 16进制的字符串
     * @return 转换后的字符串
     */
    public static String hex2Str(String hex) {
        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        //49204c6f7665204a617661 分成两个字符 49, 20, 4c...
        for (int i = 0; i < hex.length() - 1; i += 2) {
            //成对抓住十六进制
            String output = hex.substring(i, (i + 2));
            //将十六进制转换为十进制
            int decimal = Integer.parseInt(output, 16);
            //将十进制转换为字符
            sb.append((char) decimal);
            temp.append(decimal);
        }
        return sb.toString();
    }



    /**
     * 获取系统当前时间
     *
     * @return string类型的时间
     */
    public static String obtainByTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        return df.format(new Date());// new Date()为获取当前系统时间
    }



    /**
     * 合并数组
     *
     * @param firstArray  第一个数组
     * @param secondArray 第二个数组
     * @return 合并后的数组
     */
    public static byte[] concat(byte[] firstArray, byte[] secondArray) {
        if (firstArray == null || secondArray == null) {
            return null;
        }
        byte[] bytes = new byte[firstArray.length + secondArray.length];
        System.arraycopy(firstArray, 0, bytes, 0, firstArray.length);
        System.arraycopy(secondArray, 0, bytes, firstArray.length, secondArray.length);

       /* System.out.println("合并后的数组:" + bytes.toString());
        System.out.println("合并之前数组长度:" + secondArray.length + "\t合并之后数组长度:" + bytes.length);*/
        return bytes;
    }
    /**
     * 求校验和的算法
     *
     * @param b 需要求校验和的字节数组
     * @return 校验和
     */
    public static int sumCheck(byte[] b) {
        int sum = 0;
        for (int i = 0; i < b.length; i++) sum = sum + b[i];
        //转二进制
        String str = Integer.toBinaryString(sum);
        for (int i = str.length(); i < 8; i++)  str = '0' + str;
        //取后八位
        int length = str.length() - 8;
        if (length >= 0) str = str.substring(length);
        //将后八位转int
        int num = Integer.parseInt(str,2);
        //System.out.println("str:"+str+"\t\tnum:" + num);
        return num;
    }


    /**
     * CS和校验
     * @param Abyte 求和的数组
     * @return
     */
    public static byte Check_CS(byte[] Abyte) {
        byte result;
        try {
            int num = 0;
            for (int i = 0; i < Abyte.length; i++) {
                num = (num + Abyte[i]) % 256;
            }
            result = (byte) num;
        } catch (Exception e) {
            result = 0;
        }
        return result;
    }
    /**
     * 创建自定义长度byte数组
     * @param buf 数组
     * @param len 长度
     * @return
     */
    public static byte[] establishTimeByte(byte[] buf,int len,int k){
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
     * @return低字节数组
     */
    public static byte[] toLowByte(byte[] buf,int length,int k){
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

    /**
     * 将byte数组反转再转成string
     * @param buf 时间byte数组
     * @return String
     */
    public static String byteToDate(byte[] buf){
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
}
