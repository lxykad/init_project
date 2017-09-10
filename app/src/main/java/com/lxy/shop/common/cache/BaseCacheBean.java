package com.lxy.shop.common.cache;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by lxy
 */

public abstract class BaseCacheBean implements Serializable {
    /**
     * 默认有效期限是1小时： 60 * 60 * 1000
     */
    private static final long EXPIRE_LIMIT = 60 * 60 * 1000;
    private long mCreateTime;

    public BaseCacheBean() {
        this.mCreateTime = System.currentTimeMillis();
    }

    public String toString() {

        return new Gson().toJson(this);
    }


    /**
     * 子类重写该方法 修改过期时间即可
     * @return true 表示过期
     */
    public boolean isExpire() {

        //当前时间-保存时间如果超过1天，则认为过期
        return System.currentTimeMillis() - mCreateTime > EXPIRE_LIMIT;
    }

}
