package com.jugan.entity.type;

import com.jugan.entity.Common.InfoTime;
import lombok.Data;

/**
 * 建筑消防设施部件模拟量值<p/>
 * 共 10 个字节,不包括时间<p/>
 * 编号 < 3 >
 * @Author CL
 * @Date 2019/5/24-12:02
 */
@Data
public class BuildPartsAnalog extends InfoTime{
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
    private int analogType;

    /**
     * 模拟量值
     * (两个字节)
     */
    private int analogValues;
}
