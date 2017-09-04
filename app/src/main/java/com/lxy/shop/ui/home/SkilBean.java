package com.lxy.shop.ui.home;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lxy on 2017/5/11.
 */

public class SkilBean implements Serializable {

    /**
     * _id : 5913cd08421aa90c7d49ad80
     * createdAt : 2017-05-11T10:31:36.254Z
     * desc : 找到阻碍你 Android App 性能的罪魁祸首！
     * images : ["http://img.gank.io/7bef123d-8055-47de-a1d0-f70e69b9430d"]
     * publishedAt : 2017-05-11T12:03:09.581Z
     * source : chrome
     * type : Android
     * url : https://github.com/seiginonakama/BlockCanaryEx
     * used : true
     * who : 代码家
     */

    public String _id;
    public String createdAt;
    public String desc;
    public String publishedAt;
    public String source;
    public String type;
    public String url;
    public boolean used;
    public String who;
    public List<String> images;


}
