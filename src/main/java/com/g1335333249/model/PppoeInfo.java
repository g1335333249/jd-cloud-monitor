package com.g1335333249.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @Author: guanpeng
 * @Date: Create at 2021/6/30 13:46
 * @Description:
 * @Modified By:
 */
@Data
public class PppoeInfo {
    private String username;
    private String password;
    private String ipaddr;
    private String dns1;
    private String dns2;
    @SerializedName("dns_enabled")
    private int dnsEnabled;
    private int mtu;
}
