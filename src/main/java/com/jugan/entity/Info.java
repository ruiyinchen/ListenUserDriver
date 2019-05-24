package com.jugan.entity;

import com.jugan.entity.upBound.BuildParts;
import com.jugan.entity.upBound.BuildSystem;
import com.jugan.entity.upBound.UserFunction;
import lombok.Data;

import java.util.List;

/**
 * @Author CL
 * @Date 2019/3/25-15:32
 */
@Data
public class Info {
    private int head0;//头信息(1个字节)
    private int head1;//头信息(1个字节)
    private int serialNumber;//业务流水号(2个字节)
    private int systemProtcolVersion;//系统版本协议(1个字节)
    private int userProtcolVersion;//用户版本协议(1个字节)
    private String time;//数据发出时间(6个字节)
    private String sourceAddress;//源地址(6个字节)
    private String destinationAddress;//目的地址(6个字节)
    private int dataLength;//应用单元数据长度(2个字节)
    private int command;//命令(1个字节)
    private int infoType;//信息对象类型
    private int infoNum;//信息对象数目
    private List<UserFunction> userFunctions;
    private List<BuildSystem> buildSystems;
    private List<BuildParts> buildParts;

    private int crc;//校验和(1个字节)
    private int end0;//结束符(1个字节)
    private int end1;//结束符(1个字节)
}
