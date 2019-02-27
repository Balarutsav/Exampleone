package com.example.dhruvil.spit_it_out.webservices;

import com.example.dhruvil.spit_it_out.Models.Mobile;
import com.google.gson.JsonElement;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @FormUrlEncoded
    @POST("confirm_tel.php")
    Call<ResponseBody> gettoken(@Field("to") String to, @Field("token") String token);

    @FormUrlEncoded
    @POST("confirm_tel_store.php")
    Call<JsonElement> updateOTPOnServer(
            @Field("to") String to,
            @Field("gcm_regid") String gcm_regid,
            @Field("platform") String platform);


    @FormUrlEncoded
    @POST("register.php")
    Call<Mobile> registeruser(@Field("regid") String regid, @Field("tel") String tel, @Field("platform") String platform);

}
