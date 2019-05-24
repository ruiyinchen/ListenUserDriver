package com.jugan.entity.upBound;

import com.jugan.entity.Common.InfoTime;
import lombok.Data;

/**
 * 建筑消防设施系统状态
 * 共4个字节
 * 编号 < 1 >
 * @Author CL
 * @Date 2019/3/26-10:33
 */
@Data
public class BuildSystem extends InfoTime{
    /**
     * 系统类型标志
     */
    private int systemType;
    /**
     * 系统地址
     */
    private int systemAddress;
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
     * 主电源
     * (1 故障, 0 正常)
     */
    private int mps;
    /**
     * 备用电源
     * (1 故障, 0 正常)
     */
    private int eps;
    /**
     * 总线
     * (1 故障, 0 正常)
     */
    private int mainLine;
    /**
     * 手动或自动
     * (1 手动, 0 自动)
     */
    private int manualOrAutomatic;
    /**
     * 配置
     * (1 改变, 0 无变化)
     */
    private int configure;
    /**
     * 复位
     * (1 复位, 0 正常)
     */
    private int reset;
    /**
     * 预留0
     */
    private int reserve0;
    /**
     * 预留1
     */
    private int reserve1;


}
