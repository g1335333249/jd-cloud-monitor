package com.g1335333249;

import com.g1335333249.service.JdCloudWifiService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: guanpeng
 * @Date: Create at 2021/6/30 16:52
 * @Description:
 * @Modified By:
 */
@SpringBootTest(classes = JdCloudWifiApplication.class)
public class JdCloudWifiServiceTest {
    @Autowired
    private JdCloudWifiService jdCloudWifiService;
    private static String sessionId = "3fd894d3a9670ef6b234b5b1551e0beb";

//    @BeforeEach
    public void init() {
    }

    @DisplayName("获取WAN口信息")
    @Test
    public void getWanInfo() {

    }
}
