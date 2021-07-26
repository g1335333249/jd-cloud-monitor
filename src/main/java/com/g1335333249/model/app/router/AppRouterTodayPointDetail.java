package com.g1335333249.model.app.router;

import lombok.Data;

import java.util.List;

@Data
public class AppRouterTodayPointDetail {

    /**
     * todayDate : 2021-07-03
     * pointInfos : [{"mac":"DCD87C234497","todayPointIncome":238,"allPointIncome":1628},{"mac":"DCD87C2345F7","todayPointIncome":10,"allPointIncome":70}]
     * pageInfo : {"currentPage":1,"pageSize":15,"totalRecord":2,"totalPage":1}
     */

    private String todayDate;
    private AppRouterPageInfo pageInfo;
    private List<AppRouterTodayPointInfo> pointInfos;
}
