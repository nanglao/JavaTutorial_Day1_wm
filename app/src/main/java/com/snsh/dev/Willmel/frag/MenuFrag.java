package com.snsh.dev.Willmel.frag;

import android.content.Context;
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
import android.widget.Spinner;

import com.snsh.dev.Willmel.R;
import com.snsh.dev.Willmel.adapter.BestDealHomeAdapt;
import com.snsh.dev.Willmel.adapter.HotSellingHomeAdapt;
import com.snsh.dev.Willmel.model.BestDealModel;
import com.snsh.dev.Willmel.rest.RequestInterface;
import com.snsh.dev.Willmel.rest.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link //MenuFrag.//OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MenuFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RequestInterface requestInterface;
    private RecyclerView rv_hot_sale;
    private List<BestDealModel> dataList = new ArrayList<>();
    private HotSellingHomeAdapt mAdapter;


    private RecyclerView rv_shirts;
    private List<BestDealModel> dataList1 = new ArrayList<>();
    private HotSellingHomeAdapt mAdapter1;

    private RecyclerView rv_bottoms;
    private List<BestDealModel> dataList2 = new ArrayList<>();
    private HotSellingHomeAdapt mAdapter2;

    private RecyclerView rv_outwear_jackets;
    private List<BestDealModel> dataList3 = new ArrayList<>();
    private HotSellingHomeAdapt mAdapter3;

    private RecyclerView rv_underwear_and_loungewear;
    private List<BestDealModel> dataList4 = new ArrayList<>();
    private HotSellingHomeAdapt mAdapter4;

    private RecyclerView rv_Accessories;
    private List<BestDealModel> dataList5 = new ArrayList<>();
    private HotSellingHomeAdapt mAdapter5;


     Spinner spinner;

    public MenuFrag() {
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
    public static MenuFrag newInstance(String param1, String param2) {
        MenuFrag fragment = new MenuFrag();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spinner = (Spinner)view.findViewById(R.id.spinner);

        rv_hot_sale = (RecyclerView) view.findViewById(R.id.rv_hot_sale);
        rv_shirts = (RecyclerView) view.findViewById(R.id.rv_shirts);
        rv_bottoms = (RecyclerView) view.findViewById(R.id.rv_bottoms);
        rv_outwear_jackets = (RecyclerView) view.findViewById(R.id.rv_outwear_jackets);
        rv_underwear_and_loungewear = (RecyclerView) view.findViewById(R.id.rv_underwear_and_loungewear);
        rv_Accessories = (RecyclerView) view.findViewById(R.id.rv_Accessories);

        //Hot Sale layout
        mAdapter = new HotSellingHomeAdapt(dataList);

        rv_hot_sale.setHasFixedSize(true);
        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
        rv_hot_sale.setLayoutManager(mLayoutManager);
        rv_hot_sale.setItemAnimator(new DefaultItemAnimator());
        rv_hot_sale.setAdapter(mAdapter);


        //Shirts layout
        mAdapter1 = new HotSellingHomeAdapt(dataList1);

        rv_shirts.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
        rv_shirts.setLayoutManager(gridLayoutManager);
        rv_shirts.setItemAnimator(new DefaultItemAnimator());
        rv_shirts.setAdapter(mAdapter1);


        //Bottoms layout
        mAdapter2 = new HotSellingHomeAdapt(dataList2);

        rv_bottoms.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
        rv_bottoms.setLayoutManager(gridLayoutManager1);
        rv_bottoms.setItemAnimator(new DefaultItemAnimator());
        rv_bottoms.setAdapter(mAdapter2);

        //Outwear and Jackets layout
        mAdapter3 = new HotSellingHomeAdapt(dataList3);

        rv_outwear_jackets.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
        rv_outwear_jackets.setLayoutManager(gridLayoutManager2);
        rv_outwear_jackets.setItemAnimator(new DefaultItemAnimator());
        rv_outwear_jackets.setAdapter(mAdapter3);


        //Underwear and Loungewear layout
        mAdapter4 = new HotSellingHomeAdapt(dataList4);

        rv_underwear_and_loungewear.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager3 = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
        rv_underwear_and_loungewear.setLayoutManager(gridLayoutManager3);
        rv_underwear_and_loungewear.setItemAnimator(new DefaultItemAnimator());
        rv_underwear_and_loungewear.setAdapter(mAdapter4);


        //Accessories layout
        mAdapter5 = new HotSellingHomeAdapt(dataList5);

        rv_Accessories.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager4 = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
        rv_Accessories.setLayoutManager(gridLayoutManager4);
        rv_Accessories.setItemAnimator(new DefaultItemAnimator());
        rv_Accessories.setAdapter(mAdapter5);

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

                dataList3 = response.body();
                mAdapter3.addData(dataList3);

                dataList4 = response.body();
                mAdapter4.addData(dataList4);

                dataList5 = response.body();
                mAdapter5.addData(dataList5);
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
