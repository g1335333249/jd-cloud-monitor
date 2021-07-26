package com.g1335333249.model;

import lombok.Data;

@Data
public class WebStorageInterGetMode {
    private String mode;
    private String total;
    private String used;
    private String avail;
    private int status;
}
