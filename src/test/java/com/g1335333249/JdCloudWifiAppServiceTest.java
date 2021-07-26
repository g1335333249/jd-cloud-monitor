package com.g1335333249;

import com.g1335333249.model.app.AppDeviceResult;
import com.g1335333249.model.app.router.*;
import com.g1335333249.service.JdCloudWifiAppService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = JdCloudWifiApplication.class)
public class JdCloudWifiAppServiceTest {
    @Autowired
    private JdCloudWifiAppService jdCloudWifiAppService;

    @Test
    public void listAllUserDevices() {
        List<AppDeviceResult> appDeviceResults = jdCloudWifiAppService.listAllUserDevices();
        System.out.println(appDeviceResults);
    }

    @Test
    public void getRouterStatusDetail() {
        jdCloudWifiAppService.getRouterStatusDetail("feedId");
    }

    @Test
    public void getPcdnStatus() {
        jdCloudWifiAppService.getPcdnStatus("feedId");
    }

    @Test
    public void todayPointIncome() {
        AppRouterTodayPointIncome appRouterTodayPointIncome = jdCloudWifiAppService.todayPointIncome();
        System.out.println(appRouterTodayPointIncome);
    }

    @Test
    public void pinTotalAvailPoint() {
        AppRouterPinTotalAvailPoint appRouterPinTotalAvailPoint = jdCloudWifiAppService.pinTotalAvailPoint();
        System.out.println(appRouterPinTotalAvailPoint);
    }

    @Test
    public void todayPointDetail() {
        AppRouterTodayPointDetail appRouterTodayPointDetail = jdCloudWifiAppService.todayPointDetail();
        System.out.println(appRouterTodayPointDetail);
    }

    @Test
    public void pointOperateRecords() {
        AppRouterPointOperateRecords appRouterPointOperateRecords = jdCloudWifiAppService.pointOperateRecords("mac");
        System.out.println(appRouterPointOperateRecords);
    }

    @Test
    public void routerAccountInfo() {
        AppRouterRouterAccountInfo appRouterPointOperateRecords = jdCloudWifiAppService.routerAccountInfo("mac");
        System.out.println(appRouterPointOperateRecords);
    }
}
