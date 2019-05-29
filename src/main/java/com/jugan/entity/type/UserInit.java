package com.jugan.entity.type;

import com.jugan.entity.Common.InfoTime;
import lombok.Data;

/**
 * 初始化用户信息装置<p/>
 * 共 1 个字节,不包括时间<p/>
 * 编号< 89 >
 * @Author CL
 * @Date 2019/5/24-15:56
 */
@Data
public class UserInit extends InfoTime{
    /**
     * 预留<br/>
     * 十进制<br/>
     * (1 个字节)
     */
    private int obligate;
}
