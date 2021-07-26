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
 * @since 2021-07-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("app_point_income")
public class AppPointIncome implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("device_id")
    private Long deviceId;

    @TableField("event_date")
    private Date eventDate;

    /**
     * 今日积分
     */
    @TableField("today_point_income")
    private Integer todayPointIncome;

    /**
     * 可用积分
     */
    @TableField("can_use_point_income")
    private Integer canUsePointIncome;

    /**
     * 历史总积分
     */
    @TableField("all_point_income")
    private Integer allPointIncome;


    public static final String ID = "id";

    public static final String DEVICE_ID = "device_id";

    public static final String EVENT_DATE = "event_date";

    public static final String TODAY_POINT_INCOME = "today_point_income";

    public static final String CAN_USE_POINT_INCOME = "can_use_point_income";

    public static final String ALL_POINT_INCOME = "all_point_income";

}
