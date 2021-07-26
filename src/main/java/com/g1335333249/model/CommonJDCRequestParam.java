package com.g1335333249.model;

import lombok.Data;

import java.util.List;

/**
 * @Author: guanpeng
 * @Date: Create at 2021/6/30 10:59
 * @Description:
 * @Modified By:
 */
@Data
public class CommonJDCRequestParam {
    private String jsonrpc;
    private int id;
    private String method;
    private List<Object> params;
}
