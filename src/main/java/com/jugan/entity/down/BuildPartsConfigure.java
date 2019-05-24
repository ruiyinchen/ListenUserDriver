package com.jugan.entity.down;

import com.jugan.entity.Common.InfoTime;
import lombok.Data;

/**
 * 建筑消防设施部件配置情况<p/>
 * 共 38 个字节,不包括时间<p/>
 * 编号< 67 >
 * @Author CL
 * @Date 2019/5/24-14:40
 */
@Data
public class BuildPartsConfigure extends InfoTime{
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
    /**
     * 部件类型
     * (1个字节)
     */
    private int partsType;
    /**
     * 部件地址
     * (四个字节)
     */
    private String partsAddress;

    /**
     * 系统说明
     * (31个字节)
     */
    private String explanation;
}
