package com.g1335333249.aliyun.dns.service;

import com.aliyun.alidns20150109.Client;
import com.aliyun.alidns20150109.models.*;
import com.aliyun.teaopenapi.models.Config;
import com.g1335333249.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author: guanpeng
 * @Date: Create at 2021/6/29 14:17
 * @Description:
 * @Modified By:
 */
@Service
@Slf4j
public class DomainService {
    @Value("${aliyun.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aliyun.endpoint}")
    private String endpoint;
    @Value("${aliyun.dns.domain}")
    private String domain;
    @Value("${aliyun.dns.domainPre}")
    private String domainPre;
    private static String tempIP = null;

    public void setDomainValue(String value) {
        try {
            if (StringUtils.equals(value, tempIP)) {
                return;
            }
            tempIP = value;
            Config config = new Config().setAccessKeyId(accessKeyId).setAccessKeySecret(accessKeySecret);
            config.endpoint = endpoint;
            Client client = new Client(config);
            DescribeDomainRecordsRequest describeDomainRecordsRequest = new DescribeDomainRecordsRequest().setDomainName(domain).setRRKeyWord(domainPre);
            DescribeDomainRecordsResponse describeDomainRecordsResponse = client.describeDomainRecords(describeDomainRecordsRequest);
            List<DescribeDomainRecordsResponseBody.DescribeDomainRecordsResponseBodyDomainRecordsRecord> records = describeDomainRecordsResponse.getBody().getDomainRecords().getRecord();
            log.info("describeDomainRecordsResponse is {}", JsonUtil.GSON.toJson(describeDomainRecordsResponse));
            if (CollectionUtils.isEmpty(records)) {
                log.error("无{}.{}域名记录", domainPre, domain);
                return;
            }
            DescribeDomainRecordsResponseBody.DescribeDomainRecordsResponseBodyDomainRecordsRecord record = records.get(0);
            if (StringUtils.equals(value, record.getValue())) {
                log.info("IP未发生变化");
                return;
            }
            UpdateDomainRecordRequest updateDomainRecordRequest = new UpdateDomainRecordRequest()
                    .setRecordId(record.getRecordId())
                    .setRR(record.getRR())
                    .setType(record.getType())
                    .setValue(value);
            // 复制代码运行请自行打印 API 的返回值
            UpdateDomainRecordResponse updateDomainRecordResponse = client.updateDomainRecord(updateDomainRecordRequest);
            log.info("updateDomainRecordResponse is {}", JsonUtil.GSON.toJson(updateDomainRecordResponse));
        } catch (Exception e) {
            log.error("", e);
        }
    }
}
