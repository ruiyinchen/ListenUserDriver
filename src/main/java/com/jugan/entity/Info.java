package com.jugan.entity;

import com.jugan.entity.type.*;
import lombok.Data;

import java.util.List;

/**
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
    /*上行*/
    /** 建筑消防设施操作信息  */
    private List<BuildOperate> upBuildOprates;
    /** 建筑消防设施部件模拟量值  */
    private List<BuildPartsAnalog> upBuildPartsAnalogs;
    /** 建筑消防设施部件配置情况  */
    private List<BuildPartsConfigure> upBuildPartsConfigures;
    /** 建筑消防设施部件运行状态  */
    private List<BuildPartsRun> upBuildPartRuns;
    /** 建筑消防设施软件版本  */
    private List<BuildSoftware> upBuildSoftwares;
    /** 建筑消防设施系统状态  */
    private List<BuildSystem> upBuildSystems;
    /** 建筑消防设施系统配置情况  */
    private List<BuildSystemConfigure> upBuildSystemConfigures;
    /** 建筑消防设施系统时间  */
    private List<BuildSystemTime> upBuildSystemTimes;
    /** 用户信息传输装置配置情况  */
    private List<UserConfigure> upUserConfigures;
    /** 用户信息传输装置操作信息  */
    private List<UserOperate> upUserOperates;
    /** 用户信息传输装置运行状态  */
    private List<UserRun> upUserRuns;
    /** 用户信息传输装置软件版本  */
    private List<UserSoftware> upUserSoftwares;
    /** 用户信息传输装置系统时间  */
    private List<UserSystemTime> upUserSystemTimes;

    /*下行*/
    /** 建筑消防设施操作信息  */
    private List<BuildOperate> downBuildOperates;
    /** 建筑消防设施部件模拟量值  */
    private List<BuildPartsAnalog> downBuildPartsAnalogs;
    /** 建筑消防设施部件配置情况 */
    private List<BuildPartsConfigure> downBuildPartsConfigures;
    /** 建筑消防设施部件运行状态  */
    private List<BuildPartsRun> downBuildPartRuns;
    /** 建筑消防设施软件版本  */
    private List<BuildSoftware> downBuildSoftwares;
    /** 建筑消防设施系统状态  */
    private List<BuildSystem> downBuildSystems;
    /** 建筑消防设施系统配置情况  */
    private List<BuildSystemConfigure> downBuildSystemConfigures;
    /** 建筑消防设施系统时间  */
    private List<BuildSystemTime> downBuildSystemTimes;
    /** 查岗命令  */
    private List<InspectTheSentriesCommand> downCommands;
    /** 用户信息传输装置配置情况  */
    private List<UserConfigure> downUserConfigures;
    /** 初始化用户信息装置  */
    private List<UserInit> downUserInits;
    /** 用户信息传输装置操作信息  */
    private List<UserOperate> downUserOperates;
    /** 用户信息传输装置运行状态  */
    private List<UserRun> downUserRuns;
    /** 用户信息传输装置软件版本 */
    private List<UserSoftware> downUserSoftwares;
    /** 同步用户信息传输装置时钟  */
    private List<UserSyncClock> downUserSyncClocks;
    /** 用户信息传输装置系统时间  */
    private List<UserSystemTime> downUserSystemTimes;

    /** 校验和<p/> (1个字节)  */
    private int crc;
    /** 结束符0<p/> (1个字节)  */
    private int end0;
    /** 结束符1<p/> (1个字节)  */
    private int end1;
}
