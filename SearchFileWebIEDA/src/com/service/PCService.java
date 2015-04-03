package com.service;

import com.util.TurnoffPCUtils;

/**
 * Created by Pet on 2015-04-03.
 */
public class PCService {
    //设定关机时间
    public void turnoffPC(double time) {
        TurnoffPCUtils.turnOffPC(time);
    }

    //取消关机
    public void cancelOffPC() {
        TurnoffPCUtils.cancelOffPC();
    }
}
