package com.lxy.shop.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by lxy on 2017/5/11.
 */

@Scope
@Documented
@Retention(RUNTIME)
public @interface ActivityScope {

}
