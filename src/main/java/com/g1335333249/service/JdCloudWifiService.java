package com.g1335333249.service;

import com.g1335333249.aliyun.dns.service.DomainService;
import com.g1335333249.constants.URLConstant;
import com.g1335333249.entity.DeviceInfo;
import com.g1335333249.model.*;
import com.g1335333249.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

/**
 * @Author: guanpeng
 * @Date: Create at 2021/6/30 11:29
 * @Description:
 * @Modified By:
 */
@Service
@Slf4j
public class JdCloudWifiService {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${jdcloudwifi.retryCount}")
    private Integer jdCloudWifiRetryCount;
    @Autowired
    private DomainService domainService;
    @Autowired
    private IDeviceInfoService iDeviceInfoService;
    private ThreadLocal<Boolean> hasBreak = ThreadLocal.withInitial(() -> Boolean.FALSE);
    private static final Map<Long, String> SESSION_MAP = new HashMap<>();

    private CommonJDCRequestParam buildCommonRequestParam(String sessionId, String type, String method, Object param) {
        CommonJDCRequestParam commonJDCRequestParam = new CommonJDCRequestParam();
        commonJDCRequestParam.setJsonrpc(URLConstant.JSON_RPC_2_0);
        commonJDCRequestParam.setId(1);
        commonJDCRequestParam.setMethod(URLConstant.METHOD_CALL);
        List<Object> params = new ArrayList<>();
        params.add(sessionId);
        params.add(type);
        params.add(method);
        params.add(param);
        commonJDCRequestParam.setParams(params);
        return commonJDCRequestParam;
    }

    private HttpEntity<CommonJDCRequestParam> buildHttpEntity(String sessionId, String type, String method, Object param) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(buildCommonRequestParam(sessionId, type, method, param), headers);
    }

    private <T> T buildExchange(DeviceInfo deviceInfo, String type, String method, Object param, Class<T> clazz) {
        HttpEntity<CommonJDCRequestParam> httpEntity = buildHttpEntity(StringUtils.isBlank(SESSION_MAP.get(deviceInfo.getId())) ? "00000000000000000000000000000000" : SESSION_MAP.get(deviceInfo.getId()), type, method, param);
        ParameterizedTypeReference<CommonJDCResponse<T>> parameterizedTypeReference = new ParameterizedTypeReference<CommonJDCResponse<T>>() {
        };
        ResponseEntity<CommonJDCResponse<T>> exchange = restTemplate.exchange("http://" + deviceInfo.getLoginHost() + ":" + deviceInfo.getLoginPort() + URLConstant.JDC_API, HttpMethod.POST, httpEntity, parameterizedTypeReference);
        CommonJDCResponse<T> commonJDCResponse = exchange.getBody();
        if (commonJDCResponse.getError() != null && StringUtils.isNotBlank(commonJDCResponse.getError().getMessage())) {
            if (hasBreak.get()) {
                hasBreak.set(Boolean.FALSE);
                return null;
            }
            log.error("设备{}，获取失败{}", deviceInfo.getId(), JsonUtil.GSON.toJson(commonJDCResponse));
            if (StringUtils.isBlank(login(deviceInfo))) {
                return null;
            } else {
                hasBreak.set(Boolean.TRUE);
                return buildExchange(deviceInfo, type, method, param, clazz);
            }
        }
        return commonJDCResponse.getResultInfo(clazz);
    }

    private <T> T buildLoginExchange(DeviceInfo deviceInfo, String type, String method, Object param, Class<T> clazz) {
        HttpEntity<CommonJDCRequestParam> httpEntity = buildHttpEntity(StringUtils.isBlank(SESSION_MAP.get(deviceInfo.getId())) ? "00000000000000000000000000000000" : SESSION_MAP.get(deviceInfo.getId()), type, method, param);
        ParameterizedTypeReference<CommonJDCResponse<T>> parameterizedTypeReference = new ParameterizedTypeReference<CommonJDCResponse<T>>() {
        };
        ResponseEntity<CommonJDCResponse<T>> exchange = restTemplate.exchange("http://" + deviceInfo.getLoginHost() + ":" + deviceInfo.getLoginPort() + URLConstant.JDC_API, HttpMethod.POST, httpEntity, parameterizedTypeReference);
        CommonJDCResponse<T> commonJDCResponse = exchange.getBody();
        if (commonJDCResponse.getError() != null && StringUtils.isNotBlank(commonJDCResponse.getError().getMessage())) {
            log.error("设备{}，登录失败{}", deviceInfo.getId(), JsonUtil.GSON.toJson(commonJDCResponse));
            return null;
        }
        return commonJDCResponse.getResultInfo(clazz);
    }

    /**
     * 登录
     *
     * @return
     */
    public String login(DeviceInfo deviceInfo) {
        Map<String, Object> login = new HashMap<>(3);
        login.put("username", deviceInfo.getUsername());
        login.put("password", deviceInfo.getPassword());
        login.put("timeout", 600);
        LoginResult loginResult = buildLoginExchange(deviceInfo, "session", "login", login, LoginResult.class);
        SESSION_MAP.put(deviceInfo.getId(), loginResult.getUbusRpcSession());
        return loginResult.getUbusRpcSession();
    }

    /**
     * 获取wan口信息
     *
     * @param deviceInfo
     * @return
     */
    public WanInfo getWanInfo(DeviceInfo deviceInfo) {
        WanInfo wanInfo = buildExchange(deviceInfo, "jdcapi.static", "get_wan_info", Collections.emptyMap(), WanInfo.class);
        log.info("获取IP信息{}", wanInfo.getPppoeInfo().getIpaddr());
        if (!StringUtils.equals(deviceInfo.getIpAddr(), wanInfo.getPppoeInfo().getIpaddr())) {
            log.info("IP地址改变！！！原IP{},新IP{}", deviceInfo.getIpAddr(), wanInfo.getPppoeInfo().getIpaddr());
            if (deviceInfo.getUpdateAlidns()) {
                CompletableFuture.runAsync(() -> domainService.setDomainValue(wanInfo.getPppoeInfo().getIpaddr())).whenComplete(new BiConsumer<Void, Throwable>() {
                    @Override
                    public void accept(Void t, Throwable action) {
                        deviceInfo.setIpAddr(wanInfo.getPppoeInfo().getIpaddr());
                        deviceInfo.setUpdateTime(new Date());
                        iDeviceInfoService.saveOrUpdate(deviceInfo);
                    }
                }).exceptionally(e -> {
                    log.error("更新域名解析异常", e);
                    return null;
                });
            } else {
                deviceInfo.setIpAddr(wanInfo.getPppoeInfo().getIpaddr());
                deviceInfo.setUpdateTime(new Date());
                iDeviceInfoService.saveOrUpdate(deviceInfo);
            }
        }
        return wanInfo;
    }

    /**
     * 获取网络流量
     *
     * @return
     */
    public NetworkFlow getNetworkFlow(DeviceInfo deviceInfo) {
        return buildExchange(deviceInfo, "jdcapi.static", "web_get_network_flow", Collections.emptyMap(), NetworkFlow.class);
    }

    /**
     * 获取设备信息
     *
     * @return
     */
    public BoardInfo getBoardInfo(DeviceInfo deviceInfo) {
        return buildExchange(deviceInfo, "jdcapi.static", "get_board_info", Collections.emptyMap(), BoardInfo.class);
    }

    /**
     * 获取磁盘信息
     *
     * @return
     */
    public WebStorageInterGetMode getWebStorageInterGetMode(DeviceInfo deviceInfo) {
        return buildExchange(deviceInfo, "jdcapi.static", "web_storage_inter_get_mode", Collections.emptyMap(), WebStorageInterGetMode.class);
    }

    /**
     * 获取路由信息
     *
     * @return
     */
    public WebGetRouterInfo getWebGetRouterInfo(DeviceInfo deviceInfo) {
        return buildExchange(deviceInfo, "jdcapi.static", "web_get_router_info", Collections.emptyMap(), WebGetRouterInfo.class);
    }

}
