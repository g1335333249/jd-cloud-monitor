package com.g1335333249.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author guanpeng
 * @since 2021-07-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("app_router_status_detail")
public class AppRouterStatusDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("device_id")
    private Long deviceId;

    @TableField("event_time")
    private Date eventTime;

    /**
     * 上传速度
     */
    @TableField("upload")
    private Integer upload;

    /**
     * 下载速度
     */
    @TableField("download")
    private Integer download;

    /**
     * CPU负载（%）
     */
    @TableField("cpu")
    private Double cpu;

    /**
     * 内存占用（%）
     */
    @TableField("mem")
    private Integer mem;

    @TableField("rom")
    private String rom;

    @TableField("rom_type")
    private Integer romType;

    @TableField("wan_ip")
    private String wanIp;

    @TableField("model_name")
    private String modelName;

    /**
     * 在线时间
     */
    @TableField("online_time")
    private Integer onlineTime;

    @TableField("model")
    private String model;

    @TableField("ap_mode")
    private Integer apMode;

    @TableField("sn")
    private String sn;


    public static final String ID = "id";

    public static final String DEVICE_ID = "device_id";

    public static final String EVENT_TIME = "event_time";

    public static final String UPLOAD = "upload";

    public static final String DOWNLOAD = "download";

    public static final String CPU = "cpu";

    public static final String MEM = "mem";

    public static final String ROM = "rom";

    public static final String ROM_TYPE = "rom_type";

    public static final String WAN_IP = "wan_ip";

    public static final String MODEL_NAME = "model_name";

    public static final String ONLINE_TIME = "online_time";

    public static final String MODEL = "model";

    public static final String AP_MODE = "ap_mode";

    public static final String SN = "sn";

}
