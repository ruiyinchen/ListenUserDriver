package com.jugan.entity.upBound;

import lombok.Data;

/**
 * 建筑消防设施部件运行状态
 * 共40个字节
 * 编号 < 2 >
 * @Author CL
 * @Date 2019/3/26-11:39
 */
@Data
public class BuildParts {
    /**
     * 系统类型标志
     */
    private int systemType;
    /**
     * 系统地址
     */
    private int systemAddress;
    /**
     * 部件类型
     */
    private int partsType;
    /**
     * 部件地址
     * (四个字节)
     */
    private String partsAddress;
    /**
     * 运行状态
     * (1 正常, 0 测试)
     */
    private int functionType;
    /**
     * 火警
     * (1 火警, 0 无火警)
     */
    private int fireAlarm;
    /**
     * 故障
     * (1 故障, 0 无故障)
     */
    private int fault;
    /**
     * 屏蔽
     * (1 屏蔽, 0 无屏蔽)
     */
    private int shield;
    /**
     * 监管
     * (1 监管, 0 无监管)
     */
    private int supervise;
    /**
     * 开启或停止
     * (1 启动, 0 停止)
     */
    private int startOrStop;
    /**
     * 反馈
     * (1 反馈, 0 无反馈)
     */
    private int feedback;
    /**
     * 延时
     * (1 延迟, 0 无延迟)
     */
    private int delayed;
    /**
     * 电源
     * (1 故障, 0 正常)
     */
    private int powerSupply;
    /**
     * 预留0
     */
    private int reserve0;

    /**
     * 预留1
     */
    private int reserve1;

    /**
     * 预留2
     */
    private int reserve2;

    /**
     * 预留3
     */
    private int reserve3;

    /**
     * 预留4
     */
    private int reserve4;

    /**
     * 预留5
     */
    private int reserve5;

    /**
     * 预留6
     */
    private int reserve6;

    /**
     * 部件说明
     * (31个字节)
     */
    private String partsExplain;

    /**
     * 时间
     */
    private String date;
}
