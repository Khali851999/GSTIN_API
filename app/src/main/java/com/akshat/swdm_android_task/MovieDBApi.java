package com.akshat.swdm_android_task;


import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class MovieDBApi {

    public static final String API_KEY = "7EvQzBkCZINgbme1YHPFKiuFk6d2";
    public static final String BASE_URL = "https://appyflow.in/api/";

    public static GSTService gstService = null;

    public static GSTService getGstService() {

        if (gstService == null) {

            Retrofit retrofit = new Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            gstService = retrofit.create(GSTService.class);

        }
        return gstService;
    }

    public interface GSTService {

        @GET("verifyGST/")
        Call<Model> getModel(@Query("gstNo") String gstNo,
                             @Query("key_secret") String API_KEY);


    }
}
