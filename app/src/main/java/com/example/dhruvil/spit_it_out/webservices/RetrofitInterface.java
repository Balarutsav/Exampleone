package com.example.dhruvil.spit_it_out.webservices;

import android.content.Intent;
import android.content.SharedPreferences;

import com.example.dhruvil.spit_it_out.Models.Mobile;
import com.example.dhruvil.spit_it_out.Models.Model;
import com.example.dhruvil.spit_it_out.Models.timeline;
import com.example.dhruvil.spit_it_out.activitys.CameraActivity;
import com.example.dhruvil.spit_it_out.activitys.MainActivity;
import com.google.gson.JsonElement;

import java.io.File;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;



public interface RetrofitInterface {
    @FormUrlEncoded
    @POST("confirm_tel.php")
    Call<ResponseBody> gettoken(@Field("to") String to, @Field("token") String token);

    @FormUrlEncoded
    @POST("confirm_tel_store.php")
    Call<ResponseBody> updateOTPOnServer(
            @Field("to") String to,
            @Field("gcm_regid") String gcm_regid,
            @Field("platform") String platform);

    @Multipart
    @POST("post_spits.php")
    Call<ResponseBody>postspit(
          @Part("content[regid]") RequestBody regiid,
          @Part("content[ppoint]") RequestBody ppoint,
          @Part("content[targets]") RequestBody targets,
          @Part("content[desc]") RequestBody desc,
          @Part("content[signature_spit_id]") RequestBody signature_spit_id,
          @Part("content[kind]") RequestBody kind
          ,@Part MultipartBody.Part file);


    @GET("get_spits.php")
    Call<Model>getpublicspit();


    @GET("get_private_spits.php?iduser=279")
    Call<timeline>getprivatespit();


    @FormUrlEncoded
    @POST("register.php")
    Call<Mobile> registeruser(@Field("regid") String regid, @Field("tel") String tel, @Field("platform") String platform);

}
