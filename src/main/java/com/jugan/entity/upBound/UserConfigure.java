package com.jugan.entity.upBound;

import com.jugan.entity.Common.InfoTime;
import lombok.Data;

/**
 * 用户信息传输装置配置情况
 * 共 n+1 个字节
 * 编号< 26 >
 * @Author CL
 * @Date 2019/5/24-15:20
 */
@Data
public class UserConfigure extends InfoTime{
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