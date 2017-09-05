package com.lxy.shop.common.exception;

/**
 * Created by lxy
 * 统一异常处理
 */

public class BaseException extends Exception {


    public static final int ERROR_HTTP_400 = 400;

    public static final int ERROR_HTTP_404 = 404;

    public static final int ERROR_HTTP_405 = 405;

    public static final int ERROR_HTTP_500 = 500;


    private int code;
    private String displayMessage;

    public BaseException() {

    }

    public BaseException(int code, String displayMessage) {
        this.code = code;
        this.displayMessage = displayMessage;
    }

    public int getCode() {
        return code;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }
}
