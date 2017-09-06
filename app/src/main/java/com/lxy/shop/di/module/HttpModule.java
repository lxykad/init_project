package com.lxy.shop.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lxy.shop.BuildConfig;
import com.lxy.shop.common.base.BaseApplication;
import com.lxy.shop.common.http.ParamsInterceptor;
import com.lxy.shop.common.rx.RxErrorHandler;
import com.lxy.shop.data.api.ApiService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lxy
 */

@Module
public class HttpModule {

    @Singleton
    @Provides
    public Retrofit provideRetrofit(OkHttpClient okHttpClient) {

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient);
        return builder.build();
    }

    @Singleton
    @Provides
    public OkHttpClient provideOkhttpClient(Gson gson) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();


        if (BuildConfig.DEBUG) {
            // log用拦截器
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            // 开发模式记录整个body，否则只记录基本信息如返回200，http协议版本等
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            builder.addInterceptor(logging);
        }

        // 如果使用到HTTPS，我们需要创建SSLSocketFactory，并设置到client
        return builder
                .addInterceptor(new ParamsInterceptor())
                // 连接超时时间设置
                .connectTimeout(10, TimeUnit.SECONDS)
                // 读取超时时间设置
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    @Singleton
    public ApiService provideApiService(Retrofit retrofit) {

        return retrofit.create(ApiService.class);
    }

    @Singleton
    @Provides
    public Gson provideGson() {

        Gson gson = new GsonBuilder()
                .create();

        return gson;

    }

    @Singleton
    @Provides
    public RxErrorHandler provideErrorHandler() {

        return new RxErrorHandler(BaseApplication.getInstance());
    }

}
