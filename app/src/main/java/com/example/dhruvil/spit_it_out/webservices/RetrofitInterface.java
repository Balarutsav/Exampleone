package com.example.dhruvil.spit_it_out.webservices;

import com.example.dhruvil.spit_it_out.Models.Mobile;
import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @FormUrlEncoded
    @POST("confirm_tel.php")
    Call<JsonElement> gettoken(@Field("to") String to, @Field("token") String token);

    @FormUrlEncoded
    @POST("register.php")
    Call<Mobile> registeruser(@Field("regid") int regid, @Field("tel") String tel, @Field("platform") String platform);

}
