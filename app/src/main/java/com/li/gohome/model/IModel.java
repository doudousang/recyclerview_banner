package com.li.gohome.model;


import com.li.gohome.baen.HomeModel;

import io.reactivex.Observable;

public interface IModel {
    //提供数据
    public Observable<HomeModel> getInfo();

    //存储数据
    public void setInfo(String info);
}
