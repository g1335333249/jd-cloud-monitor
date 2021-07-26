package com.g1335333249.constants;

public class AppUrlConstant {
    public static final String LIST_ALL_USER_DEVICES = "https://gw.smart.jd.com/f/service/listAllUserDevices?plat=ios&hard_platform=iPhone11,8&app_version=6.5.5&plat_version=13.7&channel=jd";
    public static final String CONTROL_DEVICE = "https://gw.smart.jd.com/f/service/controlDevice?plat=ios&hard_platform=iPhone11%2C8&app_version=6.5.5&plat_version=13.7&channel=jd";

    public static final String TODAY_POINT_INCOME = "https://router-app-api.jdcloud.com/v1/regions/cn-north-1/todayPointIncome";
    public static final String PIN_TOTAL_AVAIL_POINT = "https://router-app-api.jdcloud.com/v1/regions/cn-north-1/pinTotalAvailPoint";
    public static final String TODAY_POINT_DETAIL = "https://router-app-api.jdcloud.com/v1/regions/cn-north-1/todayPointDetail?sortField=today_point&sortDirection=DESC&currentPage=%s&pageSize=%s";
    public static final String POINT_OPERATE_RECORDS = "https://router-app-api.jdcloud.com/v1/regions/cn-north-1/pointOperateRecords:show?currentPage=1&exchangeType=1&mac=%s&pageSize=15&source=1";
    public static final String ROUTER_ACCOUNT_INFO = "https://router-app-api.jdcloud.com/v1/regions/cn-north-1/routerAccountInfo?mac=%s";
}
