package com.g1335333249.model;

import com.g1335333249.utils.JsonUtil;
import lombok.Data;

import java.util.List;

/**
 * @Author: guanpeng
 * @Date: Create at 2021/6/30 10:59
 * @Description:
 * @Modified By:
 */
@Data
public class CommonJDCResponse<T> {
    private String jsonrpc;
    private int id;
    private String method;
    private JDCResponseError error;
    private List<Object> result;
    private T resultInfo;

    public T getResultInfo(Class<T> clazz) {
        if (this.resultInfo == null && result.size() == 2) {
            this.resultInfo = JsonUtil.GSON.fromJson(JsonUtil.GSON.toJson(result.get(1)), clazz);
        }
        return this.resultInfo;
    }
}
