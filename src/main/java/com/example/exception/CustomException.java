package com.example.exception;

/**
 * @Author: ZengFK
 * @Date: 2024/3/20 19:22
 */

public class CustomException extends RuntimeException {
    private String msg;

    public CustomException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

