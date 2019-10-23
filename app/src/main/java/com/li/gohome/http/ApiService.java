package com.li.gohome.http;

import com.li.gohome.baen.HomeModel;

import io.reactivex.Observable;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("/io/flutter_app/json/home_page.json")
    Observable<HomeModel> getHomeModel();
}
