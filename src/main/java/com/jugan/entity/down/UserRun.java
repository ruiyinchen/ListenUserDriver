package com.jugan.entity.down;

import com.jugan.entity.Common.InfoTime;
import lombok.Data;

/**
 * 用户信息传输装置运行状态<p/>
 * 共 1个字节,不包括时间<p/>
 * 编号< 81 >
 * @Author CL
 * @Date 2019/3/25-17:10
 */
@Data
public class UserRun extends InfoTime{

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
