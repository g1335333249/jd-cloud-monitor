package com.g1335333249.task;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.g1335333249.aliyun.dns.service.DomainService;
import com.g1335333249.entity.*;
import com.g1335333249.model.BoardInfo;
import com.g1335333249.model.NetworkFlow;
import com.g1335333249.model.WebGetRouterInfo;
import com.g1335333249.model.WebStorageInterGetMode;
import com.g1335333249.model.app.AppDeviceInfo;
import com.g1335333249.model.app.AppDeviceResult;
import com.g1335333249.model.app.AppRouterPcdnStatus;
import com.g1335333249.model.app.AppRouterStatusDetailInfo;
import com.g1335333249.model.app.router.AppRouterRouterAccountInfo;
import com.g1335333249.model.app.router.AppRouterTodayPointDetail;
import com.g1335333249.model.app.router.AppRouterTodayPointInfo;
import com.g1335333249.service.*;
import com.g1335333249.utils.DateUtil;
import com.g1335333249.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @Author: guanpeng
 * @Date: Create at 2021/6/30 18:35
 * @Description:
 * @Modified By:
 */
@Component
@Slf4j
public class AliyunDnsTask {
    @Autowired
    private JdCloudWifiService jdCloudWifiService;
    @Autowired
    private JdCloudWifiAppService jdCloudWifiAppService;
    @Autowired
    private IDeviceInfoService iDeviceInfoService;
    @Autowired
    private INetworkStatisticsService iNetworkStatisticsService;
    @Autowired
    private IStorageStatisticsService iStorageStatisticsService;
    @Autowired
    private IAppPointIncomeService iAppPointIncomeService;
    @Autowired
    private IAppRouterStatusDetailService iAppRouterStatusDetailService;
    @Autowired
    private IAppRouterPcdnStatusService iAppRouterPcdnStatusService;

    private List<DeviceInfo> deviceInfos;

    @Qualifier("threadPoolTaskExecutor")
    @Autowired
    private ThreadPoolTaskExecutor poolTaskExecutor;
    @Autowired
    private DomainService domainService;

    @PostConstruct
    public void init() throws ParseException {
        List<AppDeviceResult> appDeviceResults = jdCloudWifiAppService.listAllUserDevices();
        log.info(JsonUtil.GSON.toJson(appDeviceResults));
        for (AppDeviceInfo appDeviceInfo : appDeviceResults.get(0).getList().stream().filter(s -> "213400001".equals(s.getProductId()) || "169500020".equals(s.getProductId())).collect(Collectors.toList())) {
            List<DeviceInfo> deviceInfoList = iDeviceInfoService.list(Wrappers.<DeviceInfo>lambdaQuery().eq(DeviceInfo::getFeedId, appDeviceInfo.getFeedId()));
            if (CollectionUtils.isEmpty(deviceInfoList)) {
                DeviceInfo deviceInfo = new DeviceInfo();
                deviceInfo.setFeedId(appDeviceInfo.getFeedId() + "");
                deviceInfo.setMac(appDeviceInfo.getDeviceId());
                deviceInfo.setCreateTime(new Date());
                deviceInfo.setIsValid(true);
                deviceInfo.setUpdateAlidns(false);
                deviceInfo.setProductId(appDeviceInfo.getProductId());
                deviceInfo.setConfigType(appDeviceInfo.getConfigType());
                deviceInfo.setProductUuid(appDeviceInfo.getProductUuid());
                deviceInfo.setDeviceName(appDeviceInfo.getDeviceName());
                deviceInfo.setOwnFlag(appDeviceInfo.getOwnFlag());
                deviceInfo.setCname(appDeviceInfo.getCname());
                deviceInfo.setDevicePageType(appDeviceInfo.getDevicePageType());
                deviceInfo.setAccessKey(appDeviceInfo.getAccessKey());
                iDeviceInfoService.saveOrUpdate(deviceInfo);
            } else {
                DeviceInfo deviceInfo = deviceInfoList.get(0);
                deviceInfo.setUpdateTime(new Date());
                deviceInfo.setProductId(appDeviceInfo.getProductId());
                deviceInfo.setConfigType(appDeviceInfo.getConfigType());
                deviceInfo.setProductUuid(appDeviceInfo.getProductUuid());
                deviceInfo.setDeviceName(appDeviceInfo.getDeviceName());
                deviceInfo.setOwnFlag(appDeviceInfo.getOwnFlag());
                deviceInfo.setCname(appDeviceInfo.getCname());
                deviceInfo.setDevicePageType(appDeviceInfo.getDevicePageType());
                deviceInfo.setAccessKey(appDeviceInfo.getAccessKey());
                iDeviceInfoService.saveOrUpdate(deviceInfo);
            }
        }
        deviceInfos = iDeviceInfoService.list(Wrappers.<DeviceInfo>lambdaQuery().eq(DeviceInfo::getIsValid, true));
//        boardInfo();
        todayPointDetail();
    }

    //    @Scheduled(cron = "0 * * * * *")
    public void exec() {
        deviceInfos.stream().filter(DeviceInfo::getUpdateAlidns).forEach(s -> jdCloudWifiService.getWanInfo(s));
    }

    //    @Scheduled(cron = "0 0 * * * *")
    public void boardInfo() {
        for (DeviceInfo deviceInfo : deviceInfos) {
            try {
                BoardInfo boardInfo = jdCloudWifiService.getBoardInfo(deviceInfo);
                if (boardInfo != null && StringUtils.isNotBlank(boardInfo.getMac())) {
                    deviceInfo.setFeedId(boardInfo.getFeedId());
                    deviceInfo.setPlatform(boardInfo.getPlatform());
                    deviceInfo.setProductName(boardInfo.getProductName());
                    deviceInfo.setVersion(boardInfo.getVersion());
                    deviceInfo.setMacAddr(boardInfo.getMacAddr());
                    deviceInfo.setMac(boardInfo.getMac());
                    deviceInfo.setUpdateTime(new Date());
                    deviceInfo.setIpAddr(boardInfo.getIpAddr());
                    deviceInfo.setWanDns(boardInfo.getWanDns());
                    iDeviceInfoService.saveOrUpdate(deviceInfo);
                }
                WebGetRouterInfo webGetRouterInfo = jdCloudWifiService.getWebGetRouterInfo(deviceInfo);
                if (webGetRouterInfo != null) {
                    deviceInfo.setStorage(webGetRouterInfo.getStorage());
                    deviceInfo.setSn(webGetRouterInfo.getSn());
                    deviceInfo.setRouterVersion(webGetRouterInfo.getVersion());
                    deviceInfo.setOnlineTime(webGetRouterInfo.getOnlineTime());
                    deviceInfo.setUpdateTime(new Date());
                    iDeviceInfoService.saveOrUpdate(deviceInfo);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //    @Scheduled(cron = "10 0/5 * * * *")
    public void network() {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.SECOND, 0);
        instance.set(Calendar.MILLISECOND, 0);
        for (DeviceInfo deviceInfo : deviceInfos) {
            try {
                NetworkFlow networkFlow = jdCloudWifiService.getNetworkFlow(deviceInfo);
                if (networkFlow != null && !CollectionUtils.isEmpty(networkFlow.getData())) {
                    List<List<String>> data = networkFlow.getData();
                    List<String> strings = data.get(data.size() - 1);
                    NetworkStatistics networkStatistics = new NetworkStatistics();
                    networkStatistics.setDeviceId(deviceInfo.getId());
                    networkStatistics.setEventTime(instance.getTime());
                    networkStatistics.setUpstreamTraffic(Double.parseDouble(strings.get(0)));
                    networkStatistics.setDownstreamTraffic(Double.parseDouble(strings.get(1)));
                    iNetworkStatisticsService.save(networkStatistics);
                    CompletableFuture.runAsync(() -> {
                        for (int i = 0; i < networkFlow.getData().size() - 1; i++) {
                            instance.add(Calendar.MINUTE, -5);
                            List<NetworkStatistics> list = iNetworkStatisticsService.list(Wrappers.<NetworkStatistics>lambdaQuery().eq(NetworkStatistics::getDeviceId, deviceInfo.getId()).eq(NetworkStatistics::getEventTime, instance.getTime()));
                            if (CollectionUtils.isEmpty(list)) {
                                NetworkStatistics networkStatisticsTemp = new NetworkStatistics();
                                networkStatisticsTemp.setDeviceId(deviceInfo.getId());
                                networkStatisticsTemp.setEventTime(instance.getTime());
                                networkStatisticsTemp.setUpstreamTraffic(Double.parseDouble(networkFlow.getData().get(i).get(0)));
                                networkStatisticsTemp.setDownstreamTraffic(Double.parseDouble(networkFlow.getData().get(i).get(1)));
                                iNetworkStatisticsService.save(networkStatisticsTemp);
                            }
                        }
                    });
                }
                WebStorageInterGetMode webStorageInterGetMode = jdCloudWifiService.getWebStorageInterGetMode(deviceInfo);
                if (webStorageInterGetMode != null) {
                    StorageStatistics storageStatistics = new StorageStatistics();
                    storageStatistics.setMode(webStorageInterGetMode.getMode());
                    storageStatistics.setDeviceId(deviceInfo.getId());
                    storageStatistics.setEventTime(instance.getTime());
                    storageStatistics.setStatus(webStorageInterGetMode.getStatus());
                    storageStatistics.setTotal(Long.parseLong(webStorageInterGetMode.getTotal()));
                    storageStatistics.setAvail(Long.parseLong(webStorageInterGetMode.getAvail()));
                    storageStatistics.setUsed(Long.parseLong(webStorageInterGetMode.getUsed()));
                    iStorageStatisticsService.save(storageStatistics);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Scheduled(cron = "0 30 7 * * *")
    public void todayPointDetail() throws ParseException {
        // 获取今日积分
        AppRouterTodayPointDetail appRouterTodayPointDetail = jdCloudWifiAppService.todayPointDetail();
        if (appRouterTodayPointDetail != null) {
            String todayDate = appRouterTodayPointDetail.getTodayDate();
            Date today = DateUtil.SIMPLE_DATE_FORMAT.parse(todayDate);
            List<AppRouterTodayPointInfo> pointInfos = appRouterTodayPointDetail.getPointInfos();
            for (AppRouterTodayPointInfo pointInfo : pointInfos) {
                deviceInfos.stream().filter(s -> StringUtils.equals(s.getMac(), pointInfo.getMac())).findFirst().ifPresent(s -> {
                    List<AppPointIncome> list = iAppPointIncomeService.list(Wrappers.<AppPointIncome>lambdaQuery().eq(AppPointIncome::getEventDate, today).eq(AppPointIncome::getDeviceId, s.getId()));
                    if (CollectionUtils.isEmpty(list)) {
                        AppPointIncome appPointIncome = new AppPointIncome();
                        appPointIncome.setAllPointIncome(pointInfo.getAllPointIncome());
                        appPointIncome.setTodayPointIncome(pointInfo.getTodayPointIncome());
                        appPointIncome.setDeviceId(s.getId());
                        appPointIncome.setEventDate(today);
                        iAppPointIncomeService.saveOrUpdate(appPointIncome);
                    } else {
                        AppPointIncome appPointIncome = list.get(0);
                        appPointIncome.setAllPointIncome(pointInfo.getAllPointIncome());
                        appPointIncome.setTodayPointIncome(pointInfo.getTodayPointIncome());
                        iAppPointIncomeService.saveOrUpdate(appPointIncome);
                    }
                });
            }
        }
        for (DeviceInfo deviceInfo : deviceInfos) {
            try {
                AppRouterRouterAccountInfo appRouterRouterAccountInfo = jdCloudWifiAppService.routerAccountInfo(deviceInfo.getMac());
                if (appRouterRouterAccountInfo != null) {
                    List<AppPointIncome> list = iAppPointIncomeService.list(Wrappers.<AppPointIncome>lambdaQuery().eq(AppPointIncome::getEventDate, DateUtil.SIMPLE_DATE_FORMAT.parse(DateUtil.SIMPLE_DATE_FORMAT.format(new Date()))).eq(AppPointIncome::getDeviceId, deviceInfo.getId()));
                    if (!CollectionUtils.isEmpty(list)) {
                        AppPointIncome appPointIncome = list.get(0);
                        appPointIncome.setCanUsePointIncome(appRouterRouterAccountInfo.getAccountInfo().getAmount());
                        iAppPointIncomeService.saveOrUpdate(appPointIncome);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Scheduled(cron = "0 * * * * *")
    public void getRouterStatusDetail() {
        for (DeviceInfo deviceInfo : deviceInfos) {
            try {
                CompletableFuture.runAsync(() -> {
                    AppRouterStatusDetailInfo routerStatusDetail = jdCloudWifiAppService.getRouterStatusDetail(deviceInfo.getFeedId());
                    AppRouterStatusDetailInfo.DataBean data = routerStatusDetail.getData();
                    AppRouterStatusDetail appRouterStatusDetail = new AppRouterStatusDetail();
                    appRouterStatusDetail.setDeviceId(deviceInfo.getId());
                    appRouterStatusDetail.setEventTime(new Date());
                    appRouterStatusDetail.setUpload(Integer.parseInt(data.getUpload()));
                    appRouterStatusDetail.setDownload(Integer.parseInt(data.getDownload()));
                    appRouterStatusDetail.setCpu(Double.parseDouble(data.getCpu()));
                    appRouterStatusDetail.setMem(Integer.parseInt(data.getMem()));
                    try {
                        appRouterStatusDetail.setWanIp(data.getWanip());
                        appRouterStatusDetail.setModelName(data.getModelName());
                        appRouterStatusDetail.setOnlineTime(Integer.parseInt(data.getOnlineTime()));
                        appRouterStatusDetail.setSn(data.getSn());
                        appRouterStatusDetail.setModel(data.getModel());
                        appRouterStatusDetail.setRom(data.getRom());
                        appRouterStatusDetail.setRomType(Integer.parseInt(data.getRomType()));
                        appRouterStatusDetail.setApMode(Integer.parseInt(data.getApMode()));
                    } catch (Exception ignored) {

                    }
                    iAppRouterStatusDetailService.save(appRouterStatusDetail);
                    if (deviceInfo.getUpdateAlidns()) {
                        CompletableFuture.runAsync(() -> domainService.setDomainValue(data.getWanip()), poolTaskExecutor).exceptionally(e -> {
                            log.error("域名更新出错。。。", e);
                            return null;
                        });
                    }
                }, poolTaskExecutor).exceptionally(e -> {
                    log.error("error", e);
                    return null;
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Scheduled(cron = "0 * * * * *")
    public void getPcdnStatus() {
        Date now = new Date();
        for (DeviceInfo deviceInfo : deviceInfos) {
            try {
                CompletableFuture.runAsync(() -> {
                    AppRouterPcdnStatus appRouterPcdnStatus = jdCloudWifiAppService.getPcdnStatus(deviceInfo.getFeedId());
                    AppRouterPcdnStatus.DataBean data = appRouterPcdnStatus.getData();
                    if (!CollectionUtils.isEmpty(data.getPcdnList())) {
                        for (AppRouterPcdnStatus.DataBean.PcdnListBean pcdnListBean : data.getPcdnList()) {
                            com.g1335333249.entity.AppRouterPcdnStatus appRouterPcdnStatus1 = new com.g1335333249.entity.AppRouterPcdnStatus();
                            appRouterPcdnStatus1.setDeviceId(deviceInfo.getId());
                            appRouterPcdnStatus1.setEventTime(now);
                            appRouterPcdnStatus1.setCacheSize(pcdnListBean.getCacheSize());
                            appRouterPcdnStatus1.setNickname(pcdnListBean.getNickname());
                            appRouterPcdnStatus1.setName(pcdnListBean.getName());
                            appRouterPcdnStatus1.setStatus(pcdnListBean.getStatus());
                            try {
                                appRouterPcdnStatus1.setPluginRunPos(pcdnListBean.getPluginRunpos());
                                appRouterPcdnStatus1.setPluginIsExt(pcdnListBean.isPluginIsext());
                            } catch (Exception ignored) {
                            }
                            iAppRouterPcdnStatusService.save(appRouterPcdnStatus1);
                        }
                    }
                }, poolTaskExecutor);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
