package com.lxy.shop.data.api;

import com.lxy.shop.common.rx.BaseBean;
import com.lxy.shop.ui.home.SkilBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by lxy on 2017/5/11.
 */
public interface ApiService {

    public static final String BASE_URL = "http://gank.io/api/data/";

    @GET("{type}/{count}/{page}")
    public Observable<BaseBean<List<SkilBean>>> getSkilList(@Path("type") String type, @Path("count") int count, @Path("page") int perPage);

}
