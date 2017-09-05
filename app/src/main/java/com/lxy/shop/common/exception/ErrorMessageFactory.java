package com.lxy.shop.common.exception;

public class ErrorMessageFactory {

    public static String create(int code) {

        String errorMsg = null;

        switch (code) {

            case BaseException.ERROR_HTTP_400:

                errorMsg = "网络请求错误";

                break;

            case BaseException.ERROR_HTTP_404:

                errorMsg = "服务器资源找不到";

                break;

            case BaseException.ERROR_HTTP_500:

                errorMsg = "服务器错误";

                break;
//
//            case ApiException.ERROR_API_NO_PERMISSION:
//                errorMsg = "无权限";
//                break;

            default:
                errorMsg = "未知错误";
                break;
        }

        return errorMsg;

    }
}
