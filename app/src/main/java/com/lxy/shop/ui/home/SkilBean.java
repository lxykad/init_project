package com.lxy.shop.ui.home;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lxy
 */

public class SkilBean implements Serializable {

    public String error;
    public List<RealBean> results;

    public static class RealBean implements Serializable{

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
}
