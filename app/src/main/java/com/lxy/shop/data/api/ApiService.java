package com.lxy.shop.data.api;

import com.lxy.shop.common.User;
import com.lxy.shop.ui.home.SkilBean;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by lxy
 */
public interface ApiService {

    // http://192.168.1.130:3000/opt/token
//    public static final String BASE_URL = "http://gank.io/api/data/";
    public static final String BASE_URL = "http://192.168.1.130:3000/opt/";

    @GET("{type}/{count}/{page}")
    public Observable<Response<SkilBean>> getSkilList(@Path("type") String type, @Path("count") int count, @Path("page") int perPage);

//    @GET("{type}")
//    public Observable<Response<User>> getUserInfo(@Path("type") String type);

    @POST("token")
    public Observable<Response<User>> getUserInfo();
}
