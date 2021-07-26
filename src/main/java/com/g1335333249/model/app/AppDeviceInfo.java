package com.g1335333249.model.app;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class AppDeviceInfo {

    /**
     * p_description : 京东云无线宝路由器 Pro 是一款基于高通平台的Wi-Fi6 路由器，双频并发最高连接速率可达1800Mbps。同时该产品内置边缘计算功能，用户可以通过开启边缘计算功能获取收益。
     * product_id : 213400001
     * status : 1
     * config_type : 1903
     * device_type : 0
     * support_share : 1
     * deviceId_ble :
     * sub_devices : []
     * device_id : DCD87C2345F7
     * card_set_status : 1
     * cid : 106001
     * main_sub_type : 0
     * app_pic : https://img30.360buyimg.com/smartcloud/jfs/t1/121169/18/19174/10028/5fb4ef4dE629ce6cd/fba530625240859e.png
     * feed_id : 939541624638461674
     * version : 2.0
     * product_uuid : 7BEE10
     * stream_type_list : []
     * device_name : 移动
     * p_img_url : https://img30.360buyimg.com/smartcloud/s200x200_jfs/t1/125301/11/17250/27203/5fa39909E9272e0e4/7155db62d2d06b44.png
     * own_flag : 1
     * create_time : 1624638462000
     * access_key : babee85da002f64a4e0dcdfe2a3590c9
     * device_page_type : native
     * cname : 路由器
     * c_img_url : https://img30.360buyimg.com/smartcloud/jfs/t3052/113/615455972/2759/1dfb79b0/57bbc180Nee69703e.png
     */
    @SerializedName("p_description")
    private String pDescription;
    @SerializedName("product_id")
    private String productId;
    @SerializedName("status")
    private String status;
    @SerializedName("config_type")
    private int configType;
    @SerializedName("device_type")
    private int deviceType;
    @SerializedName("support_share")
    private int supportShare;
    @SerializedName("deviceId_ble")
    private String deviceIdBle;
    @SerializedName("device_id")
    private String deviceId;
    @SerializedName("card_set_status")
    private int cardSetStatus;
    @SerializedName("cid")
    private int cid;
    @SerializedName("main_sub_type")
    private int mainSubType;
    @SerializedName("app_pic")
    private String appPic;
    @SerializedName("feed_id")
    private long feedId;
    @SerializedName("version")
    private String version;
    @SerializedName("product_uuid")
    private String productUuid;
    @SerializedName("device_name")
    private String deviceName;
    @SerializedName("p_img_url")
    private String pImgUrl;
    @SerializedName("own_flag")
    private int ownFlag;
    @SerializedName("create_time")
    private long createTime;
    @SerializedName("access_key")
    private String accessKey;
    @SerializedName("device_page_type")
    private String devicePageType;
    @SerializedName("cname")
    private String cname;
    @SerializedName("c_img_url")
    private String cImgUrl;
    @SerializedName("sub_devices")
    private List<?> subDevices;
    @SerializedName("stream_type_list")
    private List<?> streamTypeList;
}
