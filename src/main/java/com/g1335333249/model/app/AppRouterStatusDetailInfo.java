package com.g1335333249.model.app;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: guanpeng
 * @Date: Create at 2021/7/9 16:59
 * @Description:
 * @Modified By:
 */
@Data
public class AppRouterStatusDetailInfo implements Serializable {

    /**
     * msg : OK
     * code : 0
     * data : {"upload":"31403","cpu":"40.00","mac":"DCD87C2345F7","rom":"JDC02-1.2.2.r2080","wanip":"10.61.1.23","romType":"1","download":"5349","mem":"50","model_name":"jdc-ss01","onlineTime":"185625","model":"route","ap_mode":"0","sn":"012202211800622"}
     */

    @SerializedName("msg")
    private String msg;
    @SerializedName("code")
    private String code;
    @SerializedName("data")
    private DataBean data;

    @Data
    public static class DataBean implements Serializable {
        /**
         * upload : 31403
         * cpu : 40.00
         * mac : DCD87C2345F7
         * rom : JDC02-1.2.2.r2080
         * wanip : 10.61.1.23
         * romType : 1
         * download : 5349
         * mem : 50
         * model_name : jdc-ss01
         * onlineTime : 185625
         * model : route
         * ap_mode : 0
         * sn : 012202211800622
         */
        /**
         * 上传速度
         */
        @SerializedName("upload")
        private String upload;
        /**
         * CPU使用率
         */
        @SerializedName("cpu")
        private String cpu;
        @SerializedName("mac")
        private String mac;
        @SerializedName("rom")
        private String rom;
        @SerializedName("wanip")
        private String wanip;
        @SerializedName("romType")
        private String romType;
        @SerializedName("download")
        private String download;
        /**
         * 内存使用率
         */
        @SerializedName("mem")
        private String mem;
        @SerializedName("model_name")
        private String modelName;
        /**
         * 在线时间
         */
        @SerializedName("onlineTime")
        private String onlineTime;
        @SerializedName("model")
        private String model;
        @SerializedName("ap_mode")
        private String apMode;
        @SerializedName("sn")
        private String sn;
    }
}
