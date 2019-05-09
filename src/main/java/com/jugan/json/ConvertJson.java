package com.jugan.json;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jugan.entity.Info;
import com.jugan.tools.Utilty;

/**
 * @Author CL
 * @Date 2019/4/3-11:46
 */
public class ConvertJson {
    private static ConvertJson json = new ConvertJson();
    public static ConvertJson getJson(){ return json;}

    private String name = "data";
    private String type = "ntf";
    private int seq = 1;
    private String gwid = "";
    private String ndid = "";

    /**
     * 组装json体
     * @param info 数据对象
     * @return
     */
    public ObjectNode toJsonNode(Info info){
        if (info == null) return null;
        //获得ndid
        StringBuilder sb = new StringBuilder();
        sb.append("FF");
        //获得获取已有消防系统编码
        String infoType = ConvertJson.getJson().getInfoType(info.getInfoType());
        sb.append(infoType);


        //组装body体
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        root.put("name",this.name);
        root.put("type",this.type);
        root.put("seq",this.seq);
        root.put("gwid",this.gwid);
        ArrayNode firstArray = mapper.createArrayNode();
        //这个部分是个循环体(总)
        ObjectNode secondNode = mapper.createObjectNode();
        secondNode.put("ndid",this.ndid);
        secondNode.put("time", Utilty.obtainByTime());
        ArrayNode secondArray = mapper.createArrayNode();
        //通道部分(循环)<内部>
        ObjectNode thirdNode = mapper.createObjectNode();
        thirdNode.put("chno",1);
        thirdNode.put("value",1);
        //以上通道部分(循环)<内部>
        secondArray.add(thirdNode);
        secondNode.put("channel",secondArray);
        firstArray.add(secondNode);
        //以上部分是循环体(总)
        root.put("data",firstArray);

        return root;

    }

    /**
     * 获取已有消防系统编码
     * @param infoType 数据类型
     * @return
     */
    private String getInfoType(int infoType){
        String type = null;
        switch (infoType) {
            case 1://上传建筑消防设施系统状态
                type = "01";
                break;
            case 2://上传建筑消防设施部件运行状态
                type = "02";
                break;
            case 21://上传用户信息传输装置运行状态
                type = "03";
                break;
        }
        return type;
    }
}
