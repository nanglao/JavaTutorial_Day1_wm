package com.snsh.dev.Willmel.frag;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.snsh.dev.Willmel.activity.BestDealHome_SeeMore;
import com.snsh.dev.Willmel.R;
import com.snsh.dev.Willmel.activity.PeopleYouMay_SeeMore;
import com.snsh.dev.Willmel.adapter.BestDealHomeAdapt;
import com.snsh.dev.Willmel.adapter.HotSellingHomeAdapt;
import com.snsh.dev.Willmel.adapter.PeopleYouMayHomeAdapt;
import com.snsh.dev.Willmel.model.BestDealModel;
import com.snsh.dev.Willmel.rest.RequestInterface;
import com.snsh.dev.Willmel.rest.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.views.BannerSlider;

public class HomeFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button bestDealSeeMore,peopleyoumaySeeMore;

    RequestInterface requestInterface;
    private RecyclerView rv_best_deal;
    private List<BestDealModel> dataList = new ArrayList<>();
    private BestDealHomeAdapt mAdapter;

    private RecyclerView rv_hot_selling;
    private List<BestDealModel> dataList1 = new ArrayList<>();
    private HotSellingHomeAdapt mAdapter1;

    private RecyclerView rv_people_you_may;
    private List<BestDealModel> dataList2 = new ArrayList<>();
    private PeopleYouMayHomeAdapt mAdapter2;

    public HomeFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFrag newInstance(String param1, String param2) {
        HomeFrag fragment = new HomeFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bestDealSeeMore = (Button)view.findViewById(R.id.BtnSeeMoreBD);
        bestDealSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Click BestDeal See More", Toast.LENGTH_LONG).show();
                Intent intentBDSeeMore=new Intent(getContext(), BestDealHome_SeeMore.class);
                startActivity(intentBDSeeMore);
                Log.e("Button Click: ", "Another Intent:" );
            }
        });

        peopleyoumaySeeMore = (Button)view.findViewById(R.id.peopleyoumayseemore);
        peopleyoumaySeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Click People You May See More", Toast.LENGTH_LONG).show();
                Intent intentBDSeeMore=new Intent(getContext(), PeopleYouMay_SeeMore.class);
                startActivity(intentBDSeeMore);
                Log.e("Button Click: ", "Another Intent People You May :" );
            }
        });

        rv_best_deal = (RecyclerView) view.findViewById(R.id.rv_best_deal);
        rv_hot_selling = (RecyclerView) view.findViewById(R.id.rv_hot_selling);
        rv_people_you_may = (RecyclerView) view.findViewById(R.id.rv_people_you_may);

        BannerSlider bannerSlider = (BannerSlider) view.findViewById(R.id.banner_slider1);
        List<Banner> banners = new ArrayList<>();
        //add banner using image url
        banners.add(new RemoteBanner("https://thefancy-media-ec2.thefancy.com/1280/20170801/1477912688506963057_9562cec7bacc.jpg"));
        banners.add(new RemoteBanner("https://thefancy-media-ec5.thefancy.com/1280/20161205/1304938429003335905_2f13a7968e41.jpg"));
        banners.add(new RemoteBanner("https://thefancy-media-ec2.thefancy.com/1280/20160831/1234991651286422665_233e3c43e8f8.jpg"));
        banners.add(new RemoteBanner("https://thefancy-media-ec2.thefancy.com/commerce/original/20170310/cac598f79abe49c1921e55f70354d453.png"));
        banners.add(new RemoteBanner("https://thefancy-media-ec4.thefancy.com/1280/20160401/1125020671823317979_565b9b443231.jpg"));
        //add banner using resource drawable
        bannerSlider.setBanners(banners);


        //BestDeal layout
        mAdapter = new BestDealHomeAdapt(dataList);

        rv_best_deal.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv_best_deal.setLayoutManager(mLayoutManager);
        rv_best_deal.setItemAnimator(new DefaultItemAnimator());
        rv_best_deal.setAdapter(mAdapter);


        //HotSelling layout
        mAdapter1 = new HotSellingHomeAdapt(dataList1);

        rv_hot_selling.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
        rv_hot_selling.setLayoutManager(gridLayoutManager);
        rv_hot_selling.setItemAnimator(new DefaultItemAnimator());
        rv_hot_selling.setAdapter(mAdapter1);


        //People You May Know layout
        mAdapter2 = new PeopleYouMayHomeAdapt(dataList2);

        rv_people_you_may.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        rv_people_you_may.setLayoutManager(gridLayoutManager1);
        rv_people_you_may.setItemAnimator(new DefaultItemAnimator());
        rv_people_you_may.setAdapter(mAdapter2);

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

                dataList1 = response.body();
                mAdapter1.addData(dataList1);

                dataList2 = response.body();
                mAdapter2.addData(dataList2);
            }

            @Override
            public void onFailure(Call<List<BestDealModel>> call, Throwable t) {
                Log.e("asfd", "error" + t.getMessage());
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }




}
