package com.jugan.entity.down;

import com.jugan.entity.Common.InfoTime;
import lombok.Data;

/**
 * 查岗命令<p/>
 * 共 1 个字节,不包括时间<p/>
 * 编号< 91 >
 * @Author CL
 * @Date 2019/5/24-16:04
 */
@Data
public class InspectTheSentriesCommand extends InfoTime{
    /**
     * 查岗应答超时设定时间
     * (1 个字节)
     */
    private int inspectTheSentries;
}
