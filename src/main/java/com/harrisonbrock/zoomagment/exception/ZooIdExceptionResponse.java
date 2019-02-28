package com.harrisonbrock.zoomagment.exception;

public class ZooIdExceptionResponse {

    private String zooId;

    public ZooIdExceptionResponse(String zooId) {
        this.zooId = zooId;
    }

    public String getZooId() {
        return zooId;
    }

    public void setZooId(String zooId) {
        this.zooId = zooId;
    }
}
