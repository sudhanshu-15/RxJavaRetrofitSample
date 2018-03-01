package com.ssiddh.rxjavaretrofitsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.ssiddh.rxjavaretrofitsample.model.AndroidVersion;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://api.learn2crack.com/";

    private RecyclerView versionsRecyclerView;

    private CompositeDisposable compositeDisposable;

    private DataAdapter dataAdapter;

    private ArrayList<AndroidVersion> androidVersionArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        compositeDisposable = new CompositeDisposable();
        initRecyclerView();
        loadJSON();

    }

    private void initRecyclerView() {

        versionsRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        versionsRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        versionsRecyclerView.setLayoutManager(layoutManager);
    }

    private void loadJSON() {
        ApiCallInterface apiCallInterface = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiCallInterface.class);

        compositeDisposable.add(apiCallInterface.register()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(this::handleResponse, this::handleError));

    }

    private void handleResponse(List<AndroidVersion> androidVersionList) {
        androidVersionArrayList = new ArrayList<>(androidVersionList);
        dataAdapter = new DataAdapter(androidVersionArrayList);
        versionsRecyclerView.setAdapter(dataAdapter);
    }

    private void handleError(Throwable error) {
        Toast.makeText(this, "Error "+ error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
