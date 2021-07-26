package com.g1335333249.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author guanpeng
 * @since 2021-07-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("app_router_pcdn_status")
public class AppRouterPcdnStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 设备ID
     */
    @TableField("device_id")
    private Long deviceId;

    /**
     * 采集时间
     */
    @TableField("event_time")
    private Date eventTime;

    @TableField("plugin_run_pos")
    private String pluginRunPos;

    /**
     * 是否外置存储
     */
    @TableField("plugin_is_ext")
    private Boolean pluginIsExt;

    /**
     * 缓存容量
     */
    @TableField("cache_size")
    private Integer cacheSize;

    /**
     * 插件昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 插件名称
     */
    @TableField("name")
    private String name;

    /**
     * 插件状态
     */
    @TableField("status")
    private String status;


    public static final String ID = "id";

    public static final String DEVICE_ID = "device_id";

    public static final String EVENT_TIME = "event_time";

    public static final String PLUGIN_RUN_POS = "plugin_run_pos";

    public static final String PLUGIN_IS_EXT = "plugin_is_ext";

    public static final String CACHE_SIZE = "cache_size";

    public static final String NICKNAME = "nickname";

    public static final String NAME = "name";

    public static final String STATUS = "status";

}
