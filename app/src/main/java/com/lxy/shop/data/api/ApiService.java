package com.lxy.shop.data.api;

import com.lxy.shop.common.User;
import com.lxy.shop.ui.home.SkilBean;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by lxy
 */
public interface ApiService {
    String HEADER_API_VERSION = "Accept: application/vnd.github.v3+json";


    public static final String BASE_URL = "http://gank.io/api/data/";
//    public static final String BASE_URL = "http://192.168.1.130:3000/opt/";

    @Headers({HEADER_API_VERSION})
    @GET("{type}/{count}/{page}")
    public Observable<Response<SkilBean>> getSkilList(@Path("type") String type, @Path("count") int count, @Path("page") int perPage);

//    @GET("{type}")
//    public Observable<Response<User>> getUserInfo(@Path("type") String type);

    @POST("token")
    public Observable<Response<User>> getUserInfo();

    // 测试
    @GET("{type}/{count}/{page}")
    public Observable<SkilBean> getList(@Path("type") String type, @Path("count") int count, @Path("page") int perPage);

}
