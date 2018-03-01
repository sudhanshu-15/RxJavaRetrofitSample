package com.ssiddh.rxjavaretrofitsample;

import com.ssiddh.rxjavaretrofitsample.model.AndroidVersion;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by sudhanshu on 2/28/18.
 */

public interface ApiCallInterface {

    @GET("android/jsonarray/")
    Observable<List<AndroidVersion>> register();

}
