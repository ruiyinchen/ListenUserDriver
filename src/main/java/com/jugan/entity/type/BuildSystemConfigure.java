package com.jugan.entity.type;

import com.jugan.entity.Common.InfoTime;
import lombok.Data;

/**
 * 建筑消防设施系统配置情况<p/>
 * 共 n+3 个字节,不包括时间<p/>
 * 编号< 6 >
 * @Author CL
 * @Date 2019/5/24-14:31
 */
@Data
public class BuildSystemConfigure extends InfoTime{
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
