package com.example.conn;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {
    public static String BASEURL ="";

    public static void setBASEURL(String BASEURL) {
        ApiClient.BASEURL = BASEURL;
    }

    public Retrofit getApiClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        return retrofit;
    }
}
