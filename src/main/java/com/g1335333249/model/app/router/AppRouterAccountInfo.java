package com.g1335333249.model.app.router;

import lombok.Data;

@Data
public class AppRouterAccountInfo {
    /**
     * mac : DCD87C2345F7
     * amount : 50
     * bindAccount : jd_4bbd5a960a729
     * recentExpireAmount : 10
     * recentExpireTime : 1656455001000
     */

    private String mac;
    private int amount;
    private String bindAccount;
    private int recentExpireAmount;
    private long recentExpireTime;
}
