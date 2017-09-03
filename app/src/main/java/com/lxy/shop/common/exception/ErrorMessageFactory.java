package com.lxy.shop.common.exception;

public class ErrorMessageFactory {

    public static String create(int code) {

        String errorMsg = null;

        switch (code) {

            case BaseException.HTTP_ERROR:

                errorMsg = "网络连接错误";

                break;

            case BaseException.SOCKET_TIMEOUT_ERROR:

                errorMsg = "网络超时";

                break;
            case BaseException.SOCKET_ERROR:

                errorMsg = "网络不可用";

                break;

            case BaseException.ERROR_HTTP_400:

                errorMsg = "网络请求错误";

                break;

            case BaseException.ERROR_HTTP_404:

                errorMsg = "服务器资源找不到";

                break;

            case BaseException.ERROR_HTTP_500:

                errorMsg = "服务器错误";

                break;

            case ApiException.ERROR_API_SYSTEM:
                errorMsg = "服务器错误";
                break;

            case ApiException.ERROR_API_ACCOUNT_FREEZE:
                errorMsg = "账户被冻结";
                break;

            case ApiException.ERROR_API_NO_PERMISSION:
                errorMsg = "无权限";
                break;

            case ApiException.ERROR_API_LOGIN:
                errorMsg = "用户名或密码错误";
                break;

            default:
                errorMsg = "未知错误";
                break;
        }

        return errorMsg;

    }
}
