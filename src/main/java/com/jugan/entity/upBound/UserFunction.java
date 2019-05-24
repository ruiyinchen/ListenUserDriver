package com.jugan.entity.upBound;

import com.jugan.entity.Common.InfoTime;
import lombok.Data;

/**
 * 用户信息传输装置运行状态
 * 共 1个字节
 * 编号< 21 >
 * @Author CL
 * @Date 2019/3/25-17:10
 */
@Data
public class UserFunction extends InfoTime{

    /**
     * 监视状态
     */
    private int monitor;
    /**
     * 火警
     */
    private int fireAlarm;
    /**
     * 故障
     */
    private int fault;
    /**
     * 主电源
     */
    private int mps;
    /**
     * 备用电源
     */
    private int eps;
    /**
     * 与监控中心通信故障
     */
    private int communication;
    /**
     * 检测连接线路故障
     */
    private int line;
    /**
     * 预留
     */
    private int reserve;


}
