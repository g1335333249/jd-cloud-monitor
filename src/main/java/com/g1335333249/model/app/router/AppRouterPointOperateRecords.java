package com.g1335333249.model.app.router;

import lombok.Data;

import java.util.List;

@Data
public class AppRouterPointOperateRecords {

    /**
     * pointRecords : [{"recordType":1,"exchangeType":1,"pointAmount":10,"beanAmount":0,"createTime":1625264979000},{"recordType":1,"exchangeType":1,"pointAmount":10,"beanAmount":0,"createTime":1625177963000},{"recordType":1,"exchangeType":1,"pointAmount":10,"beanAmount":0,"createTime":1625091600000},{"recordType":1,"exchangeType":1,"pointAmount":10,"beanAmount":0,"createTime":1625005460000},{"recordType":1,"exchangeType":1,"pointAmount":10,"beanAmount":0,"createTime":1624919001000},{"recordType":2,"exchangeType":1,"pointAmount":20,"beanAmount":20,"createTime":1624873952000},{"recordType":1,"exchangeType":1,"pointAmount":10,"beanAmount":0,"createTime":1624832240000},{"recordType":1,"exchangeType":1,"pointAmount":10,"beanAmount":0,"createTime":1624745857000}]
     * pageInfo : {"currentPage":1,"pageSize":15,"totalRecord":8,"totalPage":1}
     */

    private AppRouterPageInfo pageInfo;
    private List<AppRouterPointRecord> pointRecords;


}
