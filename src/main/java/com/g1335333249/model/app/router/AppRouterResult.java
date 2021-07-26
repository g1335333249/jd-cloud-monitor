package com.g1335333249.model.app.router;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AppRouterResult<T> {

    /**
     * code : 200
     * requestId : c3g5mkj96i1t6npvbaugumsdos4k2pg3
     * error : null
     * result : {"totalAvailPoint":1608}
     */

    private int code;
    private String requestId;
    private Object error;
    private T result;

}
