package com.lxy.shop.ui.recommend.bean;

import com.lxy.shop.ui.recommend.AppBean;

import java.util.List;


public class RecommendBean {

    private List<Banner> banners;

    private List<AppBean> recommendApps;
    private List<AppBean> recommendGames;

    public List<Banner> getBanners() {
        return banners;
    }

    public void setBanners(List<Banner> banners) {
        this.banners = banners;
    }

    public List<AppBean> getRecommendApps() {
        return recommendApps;
    }

    public void setRecommendApps(List<AppBean> recommendApps) {
        this.recommendApps = recommendApps;
    }

    public List<AppBean> getRecommendGames() {
        return recommendGames;
    }

    public void setRecommendGames(List<AppBean> recommendGames) {
        this.recommendGames = recommendGames;
    }
}
