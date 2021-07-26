package com.g1335333249.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class BoardInfo {
    private String status;
    @SerializedName("feedid")
    private String feedId;
    private String platform;
    @SerializedName("product_name")
    private String productName;
    private String version;
    @SerializedName("ipaddr")
    private String ipAddr;
    @SerializedName("WanDns")
    private String wanDns;
    @SerializedName("macaddr")
    private String macAddr;
    private String mac;
}
