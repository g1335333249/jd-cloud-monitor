package com.g1335333249.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class WebGetRouterInfo {
    private String type;
    private String storage;
    @SerializedName("macaddr")
    private String macAddr;
    private String sn;
    private String version;
    @SerializedName("online_time")
    private int onlineTime;
}
