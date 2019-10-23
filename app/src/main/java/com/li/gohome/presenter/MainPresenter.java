package com.li.gohome.presenter;

import com.li.gohome.baen.HomeModel;
import com.li.gohome.model.IModel;
import com.li.gohome.model.ModelImpl;
import com.li.gohome.view.IMainView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainPresenter {
    IMainView mView;
    IModel mModel;
    HomeModel mHomeModel;

    public MainPresenter(IMainView iMainView) {
        this.mView = iMainView;
        mModel = new ModelImpl();
    }

    public void getNetWorkInfo() {
        Observable<HomeModel> observable = mModel.getInfo();
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeModel homeModel) {
                        mHomeModel = homeModel;
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        mView.getHomeModel(mHomeModel);
                    }
                });
    }

}
