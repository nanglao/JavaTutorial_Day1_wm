package com.snsh.dev.Willmel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.snsh.dev.Willmel.R;
import com.snsh.dev.Willmel.adapter.BestDealHomeSeeMoreAdapt;
import com.snsh.dev.Willmel.adapter.PeopleYouMayHomeAdapt;
import com.snsh.dev.Willmel.model.BestDealModel;
import com.snsh.dev.Willmel.rest.RequestInterface;
import com.snsh.dev.Willmel.rest.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by infoava on 10/24/2017.
 */

public class PeopleYouMay_SeeMore extends AppCompatActivity {

    private ImageButton imgBack, imgsorting, imgfilter, imgmenu_grid;
    private Toolbar toolbar;

    RequestInterface requestInterface;
    private RecyclerView rv_p_y_m_view;
    private List<BestDealModel> dataList = new ArrayList<>();
    private PeopleYouMayHomeAdapt mAdapter;



    private boolean isChecked = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_y_m_home_seemore);

        rv_p_y_m_view = (RecyclerView) findViewById(R.id.rv_p_y_m_view);


        //Hot Sale layout
        mAdapter = new PeopleYouMayHomeAdapt(dataList);

        rv_p_y_m_view.setHasFixedSize(true);
        GridLayoutManager mLayoutManager = new GridLayoutManager(getApplication(), 2, GridLayoutManager.VERTICAL, false);
        rv_p_y_m_view.setLayoutManager(mLayoutManager);
        rv_p_y_m_view.setItemAnimator(new DefaultItemAnimator());
        rv_p_y_m_view.setAdapter(mAdapter);

        loadBestDeal();

        imgBack = (ImageButton) findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(PeopleYouMay_SeeMore.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Can't Work Back Button", Toast.LENGTH_LONG).show();
                }
            }
        });

        imgmenu_grid = (ImageButton) findViewById(R.id.imgmenu_grid);
        imgmenu_grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Click Menu Grid Button", Toast.LENGTH_LONG).show();

                if (isChecked) {
                    isChecked = !isChecked;
                    imgmenu_grid.setImageResource(R.drawable.menu_list);

                    mAdapter = new PeopleYouMayHomeAdapt(dataList);

                    rv_p_y_m_view.setHasFixedSize(true);
                    // RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                    GridLayoutManager mLayoutManager = new GridLayoutManager(getApplication(), 1, GridLayoutManager.HORIZONTAL, false);
                    rv_p_y_m_view.setLayoutManager(mLayoutManager);
                    rv_p_y_m_view.setItemAnimator(new DefaultItemAnimator());
                    rv_p_y_m_view.setAdapter(mAdapter);

                } else {
                    isChecked = !isChecked;
                    imgmenu_grid.setImageResource(R.drawable.menu_grid);

                    mAdapter = new PeopleYouMayHomeAdapt(dataList);

                    rv_p_y_m_view.setHasFixedSize(true);
                    // RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                    GridLayoutManager mLayoutManager = new GridLayoutManager(getApplication(), 2, GridLayoutManager.HORIZONTAL, false);
                    rv_p_y_m_view.setLayoutManager(mLayoutManager);
                    rv_p_y_m_view.setItemAnimator(new DefaultItemAnimator());
                    rv_p_y_m_view.setAdapter(mAdapter);
                }
                loadBestDeal();

            }
        });


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void loadBestDeal() {
        requestInterface = ServiceGenerator.createService(RequestInterface.class);
        Call<List<BestDealModel>> call = requestInterface.getBestDeal();
        call.enqueue(new Callback<List<BestDealModel>>() {
            @Override
            public void onResponse(Call<List<BestDealModel>> call, Response<List<BestDealModel>> response) {
                dataList = response.body();
                mAdapter.addData(dataList);

            }

            @Override
            public void onFailure(Call<List<BestDealModel>> call, Throwable t) {
                Log.e("asfd", "error" + t.getMessage());
            }
        });

    }

}
