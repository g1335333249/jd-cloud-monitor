package com.g1335333249.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @Author: guanpeng
 * @Date: Create at 2021/6/30 13:45
 * @Description:
 * @Modified By:
 */
@Data
public class WanInfo {
    private String macaddr;
    private String proto;
    @SerializedName(value = "pppoe_info")
    private PppoeInfo pppoeInfo;
}
