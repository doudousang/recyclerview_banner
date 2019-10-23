package com.li.gohome.model;


import com.li.gohome.baen.HomeModel;
import com.li.gohome.http.RetrofitHelper;

import io.reactivex.Observable;

public class ModelImpl implements IModel {
    @Override
    public Observable<HomeModel> getInfo() {
        Observable<HomeModel> observable = post();
        return observable;
    }

    private Observable<HomeModel> post() {
        Observable<HomeModel> observable = RetrofitHelper.getInstance().getServer().getHomeModel();
        return observable;
    }

    @Override
    public void setInfo(String info) {

    }
}
