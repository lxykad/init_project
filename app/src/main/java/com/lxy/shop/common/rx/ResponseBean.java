package com.lxy.shop.common.rx;


import java.io.Serializable;
import java.util.List;

public class ResponseBean<T> implements Serializable {

    public List<T> results;
}
