package com.jugan.tools;

/**
 * 转换类
 * @Author CL
 * @Date 2019/3/26-13:32
 */
public class Convert {

    /**
     * 将数字转成bit,已char数组形式输出
     * @param num 数值
     * @return int[]
     */
    public static int[] toBinaryString(int num){
        String ss = Integer.toBinaryString(num);//将十进制数转成字符串,例如n=5 ，s = "101"
        for (int i = ss.length(); i < 8; i++)
            ss = '0' + ss;

        char[] chars = ss.toCharArray();//转换成char数组
        int[] ints = new int[chars.length];
        //将char数组转成int数组
        for (int i = 0;i<ints.length;i++)
            ints[i] = Integer.parseInt(String.valueOf(chars[i]));
        return ints;
    }

    public static void main(String[] args) {
        int[] ss = Convert.toBinaryString(51);
        for (int i = 0;i <ss.length;i++){
            System.out.print(ss[i]+" ");
        }
    }

}
