package com.snsh.dev.Willmel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.nineoldandroids.view.ViewHelper;
import com.snsh.dev.Willmel.R;
import com.snsh.dev.Willmel.adapter.BestDealHomeSeeMoreAdapt;
import com.snsh.dev.Willmel.model.BestDealModel;
import com.snsh.dev.Willmel.rest.RequestInterface;
import com.snsh.dev.Willmel.rest.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by infoava on 10/23/2017.
 */

public class BestDealHome_SeeMore extends AppCompatActivity implements ObservableScrollViewCallbacks {

    private TextView TimeView;
    private View AllCalImageView;
    private ImageButton imgBackAllCat;

    RequestInterface requestInterface;
    private RecyclerView rvBestDealSeeMore;
    private List<BestDealModel> dataList = new ArrayList<>();
    private BestDealHomeSeeMoreAdapt mAdapter;
    ObservableScrollView scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_d_home_seemore_allcal);

        scroll = (ObservableScrollView) findViewById(R.id.scroll);
        scroll.setScrollViewCallbacks(this);

        /* Initialise TimeView, AllCalImageView, and sticky view */
        TimeView = (TextView) findViewById(R.id.TimeView);
        AllCalImageView = findViewById(R.id.AllCalImageView);

        imgBackAllCat = (ImageButton) findViewById(R.id.imgBackAllCat);
        imgBackAllCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(BestDealHome_SeeMore.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                   // Toast.makeText(getApplicationContext(), "Can't Work Back Button", Toast.LENGTH_LONG).show();

                }
            }
        });

        rvBestDealSeeMore = (RecyclerView) findViewById(R.id.rvBestDealSeeMore);

        //BestDeal layout
        mAdapter = new BestDealHomeSeeMoreAdapt(dataList);

        rvBestDealSeeMore.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvBestDealSeeMore.setLayoutManager(mLayoutManager);
        rvBestDealSeeMore.setItemAnimator(new DefaultItemAnimator());
        rvBestDealSeeMore.setAdapter(mAdapter);
        loadBestDeal();

    }

    private void loadBestDeal() {

        requestInterface = ServiceGenerator.createService(RequestInterface.class);
        Call<List<BestDealModel>> call = requestInterface.getBestDeal();
        call.enqueue(new Callback<List<BestDealModel>>() {
            @Override
            public void onResponse(Call<List<BestDealModel>> call, Response<List<BestDealModel>> response) {
                dataList = response.body();
                mAdapter.addData(dataList);
                Log.e("asfd", "***" + dataList.size());
            }

            @Override
            public void onFailure(Call<List<BestDealModel>> call, Throwable t) {
                Log.e("asfd", "error" + t.getMessage());
            }
        });
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        ViewHelper.setTranslationY(AllCalImageView, scrollY / 2);
    }

    @Override
    public void onDownMotionEvent() {
    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
    }
}
