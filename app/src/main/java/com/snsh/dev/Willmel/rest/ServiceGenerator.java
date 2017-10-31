package com.snsh.dev.Willmel.rest;

import com.readystatesoftware.chuck.ChuckInterceptor;
import com.snsh.dev.Willmel.App;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    public static <S> S createService(Class<S> serviceClass) {

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new ChuckInterceptor(App.getAppContext()))
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/snsh22/nshms/master/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient).build();
        return retrofit.create(serviceClass);

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://willmel.com/product/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(httpClient).build();
//        return retrofit.create(serviceClass);

    }
}
