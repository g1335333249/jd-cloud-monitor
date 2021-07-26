package com.g1335333249.model.app;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: guanpeng
 * @Date: Create at 2021/7/9 16:33
 * @Description:
 * @Modified By:
 */
@Data
public class AppRouterStatusDetail implements Serializable {

    /**
     * control_ret : done
     * digest : -1440363460
     * streams : [{"current_value":"{\"msg\":\"OK\",\"code\":\"0\",\"data\":{\"upload\":\"31403\",\"cpu\":\"40.00\",\"mac\":\"DCD87C2345F7\",\"rom\":\"JDC02-1.2.2.r2080\",\"wanip\":\"10.61.1.23\",\"romType\":\"1\",\"download\":\"5349\",\"mem\":\"50\",\"model_name\":\"jdc-ss01\",\"onlineTime\":\"185625\",\"model\":\"route\",\"ap_mode\":\"0\",\"sn\":\"012202211800622\"}}","stream_id":"GetParams"},{"current_value":"{\n  \"cmd\" : \"get_router_status_detail\"\n}","stream_id":"SetParams"}]
     */

    @SerializedName("control_ret")
    private String controlRet;
    @SerializedName("digest")
    private String digest;
    @SerializedName("streams")
    private List<StreamsBean> streams;

    @Data
    public static class StreamsBean implements Serializable {
        /**
         * current_value : {"msg":"OK","code":"0","data":{"upload":"31403","cpu":"40.00","mac":"DCD87C2345F7","rom":"JDC02-1.2.2.r2080","wanip":"10.61.1.23","romType":"1","download":"5349","mem":"50","model_name":"jdc-ss01","onlineTime":"185625","model":"route","ap_mode":"0","sn":"012202211800622"}}
         * stream_id : GetParams
         */

        @SerializedName("current_value")
        private String currentValue;
        @SerializedName("stream_id")
        private String streamId;
    }
}
