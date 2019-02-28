package com.harrisonbrock.zoomagment.exception;

public class ZooNameExceptionResponse {
    private String name;

    public ZooNameExceptionResponse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
