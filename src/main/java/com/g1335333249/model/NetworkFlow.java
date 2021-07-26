package com.g1335333249.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * @Author: guanpeng
 * @Date: Create at 2021/6/30 18:33
 * @Description:
 * @Modified By:
 */
@Data
public class NetworkFlow {
    @SerializedName("status")
    private int status;
    @SerializedName("data")
    private List<List<String>> data;
}
