package com.g1335333249.model.app;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: guanpeng
 * @Date: Create at 2021/7/9 16:59
 * @Description:
 * @Modified By:
 */
@Data
public class AppRouterPcdnStatus implements Serializable {

    /**
     * msg : OK
     * code : 0
     * data : {"pcdn_list":[{"plugin_runpos":"分区","plugin_isext":false,"cache_size":105808280,"nickname":"插件X","name":"插件X","status":"正常"},{"plugin_runpos":"分区","plugin_isext":false,"cache_size":110293052,"nickname":"插件Y","name":"插件Y","status":"正常"}],"extstorage_exist":0,"board":"SLD256","extstorage_enable":false}
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
         * pcdn_list : [{"plugin_runpos":"分区","plugin_isext":false,"cache_size":105808280,"nickname":"插件X","name":"插件X","status":"正常"},{"plugin_runpos":"分区","plugin_isext":false,"cache_size":110293052,"nickname":"插件Y","name":"插件Y","status":"正常"}]
         * extstorage_exist : 0
         * board : SLD256
         * extstorage_enable : false
         */

        @SerializedName("extstorage_exist")
        private int extstorageExist;
        @SerializedName("board")
        private String board;
        @SerializedName("extstorage_enable")
        private boolean extstorageEnable;
        @SerializedName("pcdn_list")
        private List<PcdnListBean> pcdnList;

        @Data
        public static class PcdnListBean implements Serializable {
            /**
             * plugin_runpos : 分区
             * plugin_isext : false
             * cache_size : 105808280
             * nickname : 插件X
             * name : 插件X
             * status : 正常
             */

            @SerializedName("plugin_runpos")
            private String pluginRunpos;
            @SerializedName("plugin_isext")
            private boolean pluginIsext;
            @SerializedName("cache_size")
            private int cacheSize;
            @SerializedName("nickname")
            private String nickname;
            @SerializedName("name")
            private String name;
            @SerializedName("status")
            private String status;
        }
    }
}
