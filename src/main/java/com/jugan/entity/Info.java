package com.jugan.entity;

import com.jugan.entity.type.*;
import lombok.Data;

import java.util.List;

/**
 * 整个数据包信息
 * @Author CL
 * @Date 2019/3/25-15:32
 */
@Data
public class Info {
    /** 头信息0<p/> (1个字节)  */
    private int head0;
    /** 头信息<p/>  (1个字节) */
    private int head1;
    /** 业务流水号<p/> (2个字节)  */
    private int serialNumber;
    /** 系统版本协议<p/> (1个字节)  */
    private int systemProtcolVersion;
    /** 用户版本协议<p/> (1个字节)  */
    private int userProtcolVersion;
    /** 数据发出时间<p/> (6个字节)  */
    private String time;
    /** 源地址<p/> (6个字节)  */
    private String sourceAddress;
    /** 目的地址<p/> (6个字节)  */
    private String destinationAddress;
    /** 应用单元数据长度<p/> (2个字节)  */
    private int dataLength;
    /** 命令<p/> (1个字节)  */
    private int command;
    /** 信息对象类型<p/> (1个字节)  */
    private int infoType;
    /** 信息对象数目<p/> (1个字节)  */
    private int infoNum;

    /*信息对象*/
    /** 建筑消防设施操作信息  */
    private List<BuildOperate> buildOperates;
    /** 建筑消防设施部件模拟量值  */
    private List<BuildPartsAnalog> buildPartsAnalogs;
    /** 建筑消防设施部件配置情况  */
    private List<BuildPartsConfigure> buildPartsConfigures;
    /** 建筑消防设施部件运行状态  */
    private List<BuildPartsRun> buildPartRuns;
    /** 建筑消防设施软件版本  */
    private List<BuildSoftware> buildSoftwares;
    /** 建筑消防设施系统状态  */
    private List<BuildSystem> buildSystems;
    /** 建筑消防设施系统配置情况  */
    private List<BuildSystemConfigure> buildSystemConfigures;
    /** 建筑消防设施系统时间  */
    private List<BuildSystemTime> buildSystemTimes;
    /** 用户信息传输装置配置情况  */
    private List<UserConfigure> userConfigures;
    /** 用户信息传输装置操作信息  */
    private List<UserOperate> userOperates;
    /** 用户信息传输装置运行状态  */
    private List<UserRun> userRuns;
    /** 用户信息传输装置软件版本  */
    private List<UserSoftware> userSoftwares;
    /** 用户信息传输装置系统时间  */
    private List<UserSystemTime> userSystemTimes;
    /** 查岗命令  */
    private List<InspectTheSentriesCommand> sentriescommands;
    /** 初始化用户信息装置  */
    private List<UserInit> userInits;
    /** 同步用户信息传输装置时钟  */
    private List<UserSyncClock> userSyncClocks;

    /** 校验和<p/> (1个字节)  */
    private int crc;
    /** 结束符0<p/> (1个字节)  */
    private int end0;
    /** 结束符1<p/> (1个字节)  */
    private int end1;
}
