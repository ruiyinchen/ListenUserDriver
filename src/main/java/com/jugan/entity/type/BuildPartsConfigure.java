package com.jugan.entity.type;

import lombok.Data;

/**
 * 建筑消防设施部件配置情况
 * 共 38 个字节
 * 编号< 7 >
 * @Author CL
 * @Date 2019/5/24-14:40
 */
@Data
public class BuildPartsConfigure {
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
     * 系统说明
     * (31个字节)
     */
    private String explanation;
}
