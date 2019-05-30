package com.jugan.tools;

/**
 * 转换类
 * @Author CL
 * @Date 2019/3/26-13:32
 */
public class Convert {

    /**
     *
     * @param num
     * @return
     */
    /**
     * 将数字转成bit,已char数组形式输出
     * @param num 数值
     * @param digit 需要转换多少位
     * @return int[]
     */
    public static int[] toBinaryString(int num,int digit){
        String ss = Integer.toBinaryString(num);//将十进制数转成字符串,例如n=5 ，s = "101"
        System.out.println(ss);
        for (int i = ss.length(); i < digit; i++)
            ss = '0' + ss;

        char[] chars = ss.toCharArray();//转换成char数组
        for (int i =0;i<chars.length;i++)
            System.out.print(chars[i] + " ");
        System.out.println();
        int[] ints = new int[chars.length];
        //将char数组转成int数组
        for (int i = 0;i<ints.length;i++)
            ints[i] = Integer.parseInt(String.valueOf(chars[i]));
        for (int i =0;i<ints.length;i++)
            System.out.print(ints[i]+" ");
        System.out.println();
        return ints;
    }

    public static void main(String[] args) {
        int[] ss = Convert.toBinaryString(51,16);
        for (int i = 0;i <ss.length;i++){
            System.out.print(ss[i]+" ");
        }
    }

}
