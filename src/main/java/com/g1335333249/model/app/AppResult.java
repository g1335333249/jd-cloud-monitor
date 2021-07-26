package com.g1335333249.model.app;

import lombok.Data;

@Data
public class AppResult<T> {
    private Integer status;
    private AppResultError error;
    private T result;
}
