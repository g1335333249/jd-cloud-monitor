package com.g1335333249;

import com.g1335333249.aliyun.dns.service.DomainService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: guanpeng
 * @Date: Create at 2021/6/30 18:44
 * @Description:
 * @Modified By:
 */
@SpringBootTest(classes = JdCloudWifiApplication.class)
public class DomainServiceTest {
    @Autowired
    private DomainService domainService;

    @Test
    public void init() throws Exception {
        domainService.setDomainValue("127.0.0.1");
    }
}
