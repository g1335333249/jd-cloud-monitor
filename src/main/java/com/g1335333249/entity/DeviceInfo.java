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
@TableName("device_info")
public class DeviceInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("login_host")
    private String loginHost;

    @TableField("login_port")
    private Integer loginPort;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("feed_id")
    private String feedId;

    @TableField("platform")
    private String platform;

    @TableField("product_name")
    private String productName;

    @TableField("version")
    private String version;

    @TableField("ip_addr")
    private String ipAddr;

    @TableField("wan_dns")
    private String wanDns;

    @TableField("mac_addr")
    private String macAddr;

    @TableField("mac")
    private String mac;

    @TableField("update_alidns")
    private Boolean updateAlidns;

    @TableField("is_valid")
    private Boolean isValid;

    @TableField("storage")
    private String storage;

    @TableField("sn")
    private String sn;

    @TableField("online_time")
    private Integer onlineTime;

    @TableField("router_version")
    private String routerVersion;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    @TableField("product_id")
    private String productId;

    @TableField("config_type")
    private Integer configType;

    @TableField("product_uuid")
    private String productUuid;

    @TableField("device_name")
    private String deviceName;

    @TableField("own_flag")
    private Integer ownFlag;

    @TableField("cname")
    private String cname;

    @TableField("device_page_type")
    private String devicePageType;

    @TableField("access_key")
    private String accessKey;

    public static final String ID = "id";

    public static final String LOGIN_HOST = "login_host";

    public static final String LOGIN_PORT = "login_port";

    public static final String USERNAME = "username";

    public static final String PASSWORD = "password";

    public static final String FEED_ID = "feed_id";

    public static final String PLATFORM = "platform";

    public static final String PRODUCT_NAME = "product_name";

    public static final String VERSION = "version";

    public static final String IP_ADDR = "ip_addr";

    public static final String WAN_DNS = "wan_dns";

    public static final String MAC_ADDR = "mac_addr";

    public static final String MAC = "mac";

    public static final String UPDATE_ALIDNS = "update_alidns";

    public static final String IS_VALID = "is_valid";

    public static final String STORAGE = "storage";

    public static final String SN = "sn";

    public static final String ONLINE_TIME = "online_time";

    public static final String ROUTER_VERSION = "router_version";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    public static final String PRODUCT_ID = "product_id";

    public static final String CONFIG_TYPE = "config_type";

    public static final String PRODUCT_UUID = "product_uuid";

    public static final String DEVICE_NAME = "device_name";

    public static final String OWN_FLAG = "own_flag";

    public static final String CNAME = "cname";

    public static final String DEVICE_PAGE_TYPE = "device_page_type";

    public static final String ACCESS_KEY = "access_key";

}
