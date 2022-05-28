package com.sortscript.amdsystem.Interfaces;




import com.sortscript.amdsystem.Models.Root;
import com.sortscript.amdsystem.Models.SendData;

import retrofit2.Call;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GetDataService {

        @POST("/predict")
        Call<Root> sentData(@Body SendData data);
    }

