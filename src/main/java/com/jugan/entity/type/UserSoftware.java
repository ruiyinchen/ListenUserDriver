package com.jugan.entity.type;

import com.jugan.entity.Common.InfoTime;
import lombok.Data;

/**
 * 用户信息传输装置软件版本<p/>
 * 共 2 个字节,不包括时间<p/>
 * 编号< 25 >
 * @Author CL
 * @Date 2019/5/24-15:18
 */
@Data
public class UserSoftware extends InfoTime{
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
