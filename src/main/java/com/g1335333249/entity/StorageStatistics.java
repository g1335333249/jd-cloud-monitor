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
 * @since 2021-07-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("storage_statistics")
public class StorageStatistics implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 设备ID
     */
    @TableField("device_id")
    private Long deviceId;

    /**
     * 模式
     */
    @TableField("mode")
    private String mode;

    /**
     * 事件时间
     */
    @TableField("event_time")
    private Date eventTime;

    /**
     * 总量
     */
    @TableField("total")
    private Long total;

    /**
     * 使用
     */
    @TableField("used")
    private Long used;

    /**
     * 空闲
     */
    @TableField("avail")
    private Long avail;

    /**
     * 状态
     */
    @TableField("status")
    private Integer status;


    public static final String ID = "id";

    public static final String DEVICE_ID = "device_id";

    public static final String MODE = "mode";

    public static final String EVENT_TIME = "event_time";

    public static final String TOTAL = "total";

    public static final String USED = "used";

    public static final String AVAIL = "avail";

    public static final String STATUS = "status";

}
