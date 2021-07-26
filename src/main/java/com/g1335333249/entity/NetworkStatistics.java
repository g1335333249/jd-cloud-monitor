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
 * @since 2021-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("network_statistics")
public class NetworkStatistics implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 设备ID
     */
    @TableField("device_id")
    private Long deviceId;

    /**
     * 事件时间
     */
    @TableField("event_time")
    private Date eventTime;

    /**
     * 上行流量
     */
    @TableField("upstream_traffic")
    private Double upstreamTraffic;

    /**
     * 下行流量
     */
    @TableField("downstream_traffic")
    private Double downstreamTraffic;


    public static final String ID = "id";

    public static final String DRVICE_ID = "drvice_id";

    public static final String EVENT_TIME = "event_time";

    public static final String UPSTREAM_TRAFFIC = "upstream_traffic";

    public static final String DOWNSTREAM_TRAFFIC = "downstream_traffic";

}
