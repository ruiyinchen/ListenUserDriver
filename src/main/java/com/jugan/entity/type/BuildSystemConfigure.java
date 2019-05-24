package com.jugan.entity.type;

import lombok.Data;

/**
 * 建筑消防设施系统配置情况
 * 共 n+3 个字节
 * 编号< 6 >
 * @Author CL
 * @Date 2019/5/24-14:31
 */
@Data
public class BuildSystemConfigure {
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
     * 系统说明长度
     * (一个字节)
     */
    private int explanationLen;

    /**
     * 系统说明
     * (n个字节)
     */
    private String explanation;
}
