package com.lxy.shop.common.rx;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lxy on 2017/5/19.
 */

public class BaseBean<T> implements Serializable {

    public String message;
    public int code;
    public String error;

    public T results;

}
