package com.swimtothend.base.api;

/**
 * create by BloodFly at 2019/1/21
 */
public class BaseVO extends SerializableObject {

    // 业务流水id
    private long sid;

    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }
}
