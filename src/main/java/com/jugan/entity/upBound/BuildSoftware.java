package com.jugan.entity.upBound;

import lombok.Data;

/**
 * 建筑消防设施软件版本
 * 共四个字节
 * 编号 < 5 >
 * @Author CL
 * @Date 2019/5/24-14:12
 */
@Data
public class BuildSoftware {
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
     * 主要版本
     * (1个字节)
     */
    private int mainVersion;

    /**
     * 次要版本
     * (1个字节)
     */
    private int secondary;
}
