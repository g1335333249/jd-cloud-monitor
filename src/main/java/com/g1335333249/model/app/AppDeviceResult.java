package com.g1335333249.model.app;

import lombok.Data;

import java.util.List;

@Data
public class AppDeviceResult {
    private Integer count;
    private List<AppDeviceInfo> list;
}
