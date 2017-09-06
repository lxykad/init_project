package com.lxy.shop.common.http;

import com.lxy.shop.common.constant.Constant;
import com.lxy.shop.ui.usercenter.User;
import com.orhanobut.hawk.Hawk;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lxy
 */

public class ParamsInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        HttpUrl modifiedUrl;

        User user = Hawk.get(Constant.GLOBAL_USER);

        if (user == null) {
            modifiedUrl = request.url().newBuilder()
                    .addQueryParameter(Constant.TOKEN, "tokentest")
                    .build();
        } else {
            modifiedUrl = request.url().newBuilder()
                    .addQueryParameter(Constant.TOKEN, user.token)
                    .build();
        }

        request = request.newBuilder().url(modifiedUrl).build();
        return chain.proceed(request);

    }
}
