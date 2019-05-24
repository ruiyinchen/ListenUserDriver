package com.jugan.entity.upBound;

import com.jugan.entity.Common.InfoTime;
import lombok.Data;

/**
 * 用户信息传输装置操作信息
 * 共 2 个字节
 * 编号< 24 >
 * @Author CL
 * @Date 2019/5/24-15:12
 */
@Data
public class Useroperate extends InfoTime{
    /*以下部分占一个字节*/
    /**
     * 复位
     * (1 复位, 0 无操作)
     */
    private int reset;

    /**
     * 消音
     * (1 消音, 0 无操作)
     */
    private int noisedamping;

    /**
     * 报警
     * (1 手动报警, 0 无操作)
     */
    private int manualAlarm;

    /**
     * 警情
     * (1 消除, 0 无操作)
     */
    private int policeExterminate;

    /**
     * 自检
     * (1 自检, 0 无操作)
     */
    private int selfCheck;

    /**
     * 查岗
     * (1 应答, 0 无操作)
     */
    private int inspectTheSentries;

    /**
     * 测试
     * (1 测试 , 0 无操作)
     */
    private int test;
    /**
     * 预留1
     */
    private int obligate;

    /*以上部分占一个字节*/

    /**
     * 操作员
     * (一个字节)
     */
    private int operator;
}
