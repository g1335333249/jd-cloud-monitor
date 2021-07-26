package com.g1335333249.service;

import com.g1335333249.constants.AppUrlConstant;
import com.g1335333249.model.app.*;
import com.g1335333249.model.app.router.*;
import com.g1335333249.utils.HmacSha1Util;
import com.g1335333249.utils.JsonUtil;
import com.g1335333249.utils.MD5Util;
import com.g1335333249.utils.OkHttpUtil;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JdCloudWifiAppService {
    public static final String DEVICE_KEY = "ios6.5.5iPhone11,813.7:%s";
    public static final String AUTHORIZATION_TEMPLATE = "smart %s:::%s:::%s";
    @Value("${jdcloudwifiapp.accessKey}")
    public String accessKey;
    @Value("${jdcloudwifiapp.macKey}")
    public String hmacKey;
    @Value("${jdcloudwifiapp.tgt}")
    public String tgt;
    @Value("${jdcloudwifiapp.pin}")
    public String pin;
    public static final String POST_JSON_BODY = "%spostjson_body%s%s%s%s";
    @Autowired
    @Qualifier("supportOctetStreamRestTemplate")
    private RestTemplate restTemplate;

    public <T> AppResult<T> postRequest(String url, String body) {
        LocalDateTime now = LocalDateTime.now();
        MultiValueMap<String, String> headers = new HttpHeaders();
        String nowStr = now + "Z";
        String authorization = encodeAuthorization(body, accessKey, nowStr);
        headers.add("Authorization", authorization);
        headers.add("timestamp", now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        headers.add("accesskey", accessKey);
        headers.add("tgt", tgt);
        headers.add("User-Agent", "ios");
        headers.add("appkey", "996");
        headers.add("pin", pin);
        headers.add("Content-Type", "application/json");
        ParameterizedTypeReference<AppResult<T>> parameterizedTypeReference = new ParameterizedTypeReference<AppResult<T>>() {
        };
        ResponseEntity<AppResult<T>> exchange = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(body, headers), parameterizedTypeReference);
        AppResult<T> body1 = exchange.getBody();
        return body1;
    }

    public <T> T routerAppApiGetRequest(String url, Class<T> clazz) {
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("wskey", tgt);
        headers.add("User-Agent", "%E4%BA%AC%E4%B8%9C%E4%BA%91%E6%97%A0%E7%BA%BF%E5%AE%9D/1049 CFNetwork/1300.1 Darwin/21.0.0");
        headers.add("Content-Type", "application/json");
        ParameterizedTypeReference<AppRouterResult<Object>> parameterizedTypeReference = new ParameterizedTypeReference<AppRouterResult<Object>>() {
        };
        ResponseEntity<AppRouterResult<Object>> exchange = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), parameterizedTypeReference);
        AppRouterResult<Object> body = exchange.getBody();
        return JsonUtil.GSON.fromJson(JsonUtil.GSON.toJson(body.getResult()), clazz);
    }

    /**
     * 获取设备列表
     *
     * @return
     */
    public List<AppDeviceResult> listAllUserDevices() {
        String body = "{\"appversion\":\"2.7.3\",\"appplatform\":\"iPhone11,8\",\"appplatformversion\":\"13.7\"}";
        Object s = postRequest(AppUrlConstant.LIST_ALL_USER_DEVICES, body);
        System.out.println(JsonUtil.GSON.toJson(s));
        Type jsonType = new TypeToken<AppResult<List<AppDeviceResult>>>() {
        }.getType();
        AppResult<List<AppDeviceResult>> data = JsonUtil.GSON.fromJson(JsonUtil.GSON.toJson(s), jsonType);
        return data.getResult();
    }

    /**
     * 获取路由状态详情
     *
     * @param feedId
     * @return
     */
    public AppRouterStatusDetailInfo getRouterStatusDetail(String feedId) {
        String body = "{\"feed_id\":\"" + feedId + "\",\"command\":[{\"stream_id\":\"SetParams\",\"current_value\":\"{\\n  \\\"cmd\\\" : \\\"get_router_status_detail\\\"\\n}\"}]}";
        LocalDateTime now = LocalDateTime.now();
        Map<String, String> headers = new HashMap<>();
        String nowStr = now + "Z";
        String authorization = encodeAuthorization(body, accessKey, nowStr);
        headers.put("Authorization", authorization);
        headers.put("timestamp", now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        headers.put("accesskey", accessKey);
        headers.put("tgt", tgt);
        headers.put("User-Agent", "ios");
        headers.put("appkey", "996");
        headers.put("pin", pin);
        String s = OkHttpUtil.postJsonParams(AppUrlConstant.CONTROL_DEVICE, body, headers);
        AppResult appResult = JsonUtil.GSON.fromJson(s, AppResult.class);
        AppRouterStatusDetail appRouterStatusDetail = null;
        try {
            appRouterStatusDetail = JsonUtil.GSON.fromJson(appResult.getResult().toString(), AppRouterStatusDetail.class);
        } catch (Exception e) {
            System.out.println(JsonUtil.GSON.toJson(appResult));
        }
        return JsonUtil.GSON.fromJson(appRouterStatusDetail.getStreams().get(0).getCurrentValue(), AppRouterStatusDetailInfo.class);
    }

    /**
     * 获取路由状态详情
     *
     * @param feedId
     * @return
     */
    public AppRouterPcdnStatus getPcdnStatus(String feedId) {
        String body = "{\"feed_id\":\"" + feedId + "\",\"command\":[{\"stream_id\":\"SetParams\",\"current_value\":\"{\\n  \\\"cmd\\\" : \\\"plugin.jdcplugin_opt.get_pcdn_status\\\"\\n}\"}]}";
        LocalDateTime now = LocalDateTime.now();
        Map<String, String> headers = new HashMap<>();
        String nowStr = now + "Z";
        String authorization = encodeAuthorization(body, accessKey, nowStr);
        headers.put("Authorization", authorization);
        headers.put("timestamp", now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        headers.put("accesskey", accessKey);
        headers.put("tgt", tgt);
        headers.put("User-Agent", "ios");
        headers.put("appkey", "996");
        headers.put("pin", pin);
        String s = OkHttpUtil.postJsonParams(AppUrlConstant.CONTROL_DEVICE, body, headers);
        AppResult appResult = JsonUtil.GSON.fromJson(s, AppResult.class);
        AppRouterStatusDetail appRouterStatusDetail = JsonUtil.GSON.fromJson(appResult.getResult().toString(), AppRouterStatusDetail.class);
        return JsonUtil.GSON.fromJson(appRouterStatusDetail.getStreams().get(0).getCurrentValue(), AppRouterPcdnStatus.class);
    }

    /**
     * 获取今日收益
     *
     * @return
     */
    public AppRouterTodayPointIncome todayPointIncome() {
        return routerAppApiGetRequest(AppUrlConstant.TODAY_POINT_INCOME, AppRouterTodayPointIncome.class);
    }

    /**
     * 获取可兑换积分总数
     *
     * @return
     */
    public AppRouterPinTotalAvailPoint pinTotalAvailPoint() {
        return routerAppApiGetRequest(AppUrlConstant.PIN_TOTAL_AVAIL_POINT, AppRouterPinTotalAvailPoint.class);
    }

    /**
     * 获取今日积分详情
     *
     * @return
     */
    public AppRouterTodayPointDetail todayPointDetail() {
        AppRouterTodayPointDetail appRouterTodayPointDetail = routerAppApiGetRequest(String.format(AppUrlConstant.TODAY_POINT_DETAIL, 1, 15), AppRouterTodayPointDetail.class);
        appRouterTodayPointDetail.setPointInfos(allTodayPointDetail(1, 15, null));
        return appRouterTodayPointDetail;
    }

    public List<AppRouterTodayPointInfo> allTodayPointDetail(Integer page, Integer pageSize, List<AppRouterTodayPointInfo> result) {
        if (result == null) {
            result = new ArrayList<>();
        }
        AppRouterTodayPointDetail appRouterTodayPointDetail = routerAppApiGetRequest(String.format(AppUrlConstant.TODAY_POINT_DETAIL, page, pageSize), AppRouterTodayPointDetail.class);
        AppRouterPageInfo pageInfo = appRouterTodayPointDetail.getPageInfo();
        result.addAll(appRouterTodayPointDetail.getPointInfos());
        if (pageInfo.getTotalPage() == page) {
            return result;
        } else {
            return allTodayPointDetail(page + 1, pageSize, result);
        }
    }

    /**
     * 获取设备积分详情
     *
     * @return
     */
    public AppRouterPointOperateRecords pointOperateRecords(String mac) {
        if (StringUtils.isBlank(mac)) {
            throw new IllegalArgumentException("Mac不能为空");
        }
        return routerAppApiGetRequest(String.format(AppUrlConstant.POINT_OPERATE_RECORDS, mac), AppRouterPointOperateRecords.class);
    }

    /**
     * 获取设备账户详情
     *
     * @return
     */
    public AppRouterRouterAccountInfo routerAccountInfo(String mac) {
        if (StringUtils.isBlank(mac)) {
            throw new IllegalArgumentException("Mac不能为空");
        }
        return routerAppApiGetRequest(String.format(AppUrlConstant.ROUTER_ACCOUNT_INFO, mac), AppRouterRouterAccountInfo.class);
    }


    public String encodeAuthorization(String body, String accessKey, String nowStr) {
        String deviceKey = MD5Util.getMD5Str(String.format(DEVICE_KEY, LocalDate.now().getDayOfYear()));
        String data = String.format(POST_JSON_BODY, deviceKey, body, nowStr, accessKey, deviceKey);
        String base64HmacSHA1Encrypt = HmacSha1Util.Base64HmacSHA1Encrypt(data, hmacKey);
        return String.format(AUTHORIZATION_TEMPLATE, accessKey, base64HmacSHA1Encrypt, nowStr);
    }
}
