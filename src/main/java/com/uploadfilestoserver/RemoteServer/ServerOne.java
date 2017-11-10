package com.uploadfilestoserver.RemoteServer;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ServerOne {
    @GET("v1/external_ssos/search?accessToken=public&action=index&controller=public&locale=en")
    Call<String> getPublicSsos(
            @Header("X-API-TOKEN") String xApiToken
    );

    @POST("v1/clients/profile")
    Call<String> getprofile(
            @Query("accessToken") String accessToken,
            @Header("X-API-TOKEN") String xApiToken
    );

    @Multipart
//    @POST("portal-mediator/services/v1/clients/profile/support")
    @POST("v1/clients/profile/support")
    Call<ResponseBody> createSupport(
            @Query("accessToken") String accessToken,
            @Header("X-API-TOKEN") String xApiToken,
            @Header("Content-Type") String contentType,
            @Part("supportBody") String supportBody,
            @Part("supportType") String supportType,
            @Part List<MultipartBody.Part> supportFiles
    );
}