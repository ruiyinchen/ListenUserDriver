package com.jugan.entity.type;

import com.jugan.entity.Common.InfoTime;
import lombok.Data;

/**
 * 建筑消防设施软件版本<p/>
 * 共 4 个字节,不包括时间<p/>
 * 编号 < 5 >
 * @Author CL
 * @Date 2019/5/24-14:12
 */
@Data
public class BuildSoftware extends InfoTime{
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
