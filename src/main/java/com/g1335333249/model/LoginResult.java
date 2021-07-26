package com.g1335333249.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @Author: guanpeng
 * @Date: Create at 2021/6/30 13:51
 * @Description:
 * @Modified By:
 */
@Data
public class LoginResult {
    @SerializedName("ubus_rpc_session")
    private String ubusRpcSession;
    private int timeout;
    private int expires;
}
