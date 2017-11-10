package com.uploadfilestoserver.RemoteServer;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface ServerOne {
//    @GET("v1/external_ssos/search?accessToken=public&action=index&controller=public&locale=en")
//    Call<String> getPublicSsos(
//            @Header("X-API-TOKEN") String xApiToken
//    );
//
    @POST("v1/clients/profile")
    Call<String> getprofile(
            @Query("accessToken") String accessToken,
            @Header("X-API-TOKEN") String xApiToken
    );

    @Multipart
    @POST("/v1/clients/profile/support")
    Call<ResponseBody> createSupport(
            @Query("accessToken") String accessToken,
            @Header("X-API-TOKEN") String xApiToken,
            @Header("content-type") String contentType,
            @Part("supportBody") RequestBody supportBody,
            @Part("supportType") String supportType,
            @Part MultipartBody.Part supportFiles
    );
}