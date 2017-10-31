package com.snsh.dev.Willmel.rest;

import com.snsh.dev.Willmel.model.BestDealModel;
import com.snsh.dev.Willmel.model.SpinnerCategoryModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by infoava(nlk) on 1/5/17.
 */

public interface RequestInterface {

    @GET("sh_best_deal.json")
    Call<List<BestDealModel>> getBestDeal();

    @GET("categoryList")
    Call<List<SpinnerCategoryModel>> getCategory();


}
