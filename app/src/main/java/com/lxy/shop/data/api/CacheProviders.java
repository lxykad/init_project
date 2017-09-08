package com.lxy.shop.data.api;

import com.lxy.shop.ui.home.SkilBean;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.LifeCache;
import io.rx_cache2.Reply;
import retrofit2.Response;

/**
 * Created by lxy
 */

public interface CacheProviders {

    // 返回结果包装为reply 可以查看数据是的来源 getSource
    @LifeCache(duration = 2,timeUnit = TimeUnit.MINUTES)
    public Observable<Reply<Response<SkilBean>>> getSkilList(Observable<Response<SkilBean>> bean, DynamicKey dynamicKey, EvictDynamicKey evictDynamicKey);

}
