package com.jugan.entity.type;

import lombok.Data;

/**
 * 建筑消防设施部件模拟量值
 * 共十个字节
 * @Author CL
 * @Date 2019/5/24-12:02
 */
@Data
public class BuildAnalog {
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
     * 模拟量类型
     * (一个字节)
     */
    private String analogType;

    /**
     * 模拟量值
     * (两个字节)
     */
    private int analogValues;
}
