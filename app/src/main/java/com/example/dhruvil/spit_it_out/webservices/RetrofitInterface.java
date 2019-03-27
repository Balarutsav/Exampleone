package com.example.dhruvil.spit_it_out.webservices;

import com.example.dhruvil.spit_it_out.Models.Mobile;
import com.example.dhruvil.spit_it_out.Models.Model;
import com.example.dhruvil.spit_it_out.activitys.CameraActivity;
import com.google.gson.JsonElement;

import java.io.File;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

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

    @FormUrlEncoded
    @POST("post_spits.php")
    Call<ResponseBody>postspit(
          @Field("content[regid]") String regiid,
          @Field("content[ppoint]") String ppoint,
          @Field("content[targets]") String targets,
          @Field("content[desc]") String desc,
          @Field("content[signature_spit_id]") String signature_spit_id,
          @Field("content[kind]") String kind,
          @Field("file") File file);


    @GET("get_spits.php")
    Call<Model>getpublicspit();
    @FormUrlEncoded
    @POST("register.php")
    Call<Mobile> registeruser(@Field("regid") String regid, @Field("tel") String tel, @Field("platform") String platform);

}
