package com.jugan.entity.upBound;

import com.jugan.entity.Common.InfoTime;
import lombok.Data;

/**
 * 建筑消防设施操作信息
 * 共4个字节
 * 编号 < 4 >
 * @Author CL
 * @Date 2019/5/24-13:47
 */
@Data
public class BuildOperate extends InfoTime{
    /**
     * 系统类型标志
     * (1个字节)
     */
    private int systemType;
    /**
     * 系统地址
     * (1个字节)
     */
    private int systemAddress;

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
     * 确认
     * (1 确认, 0 无操作)
     */
    private int validation;

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
