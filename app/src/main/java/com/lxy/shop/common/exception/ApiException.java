package com.lxy.shop.common.exception;

/**
 * Created by lxy on 2017/6/6.
 */

public class ApiException extends BaseException{

    public ApiException(int code, String displayMessage) {
        super(code, displayMessage);
    }
}
