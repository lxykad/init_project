package com.lxy.shop.data.api;

import com.lxy.shop.ui.home.SkilBean;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by lxy
 */
public interface ApiService {

    public static final String BASE_URL = "http://gank.io/api/data/";

    @GET("{type}/{count}/{page}")
    public Observable<Response<SkilBean>> getSkilList(@Path("type") String type, @Path("count") int count, @Path("page") int perPage);

}
