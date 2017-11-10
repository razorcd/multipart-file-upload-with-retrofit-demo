package com.uploadfilestoserver;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;


public interface ServerInterface {
    @GET("v1/external_ssos/search")
    @Headers("X-API-TOKEN: "+ resources.Properties.X_API_TOKEN)
    Call<String> getPublicSsos();

    @GET("v1/clients/profile")
    @Headers("X-API-TOKEN: "+ resources.Properties.X_API_TOKEN)
    Call<String> getprofile(
            @Query("accessToken") String accessToken
    );

    @Multipart
//    @POST("portal-mediator/services/v1/clients/profile/support")
    @POST("v1/clients/profile/support")
    @Headers({"Content-Type: multipart/form-data", "X-API-TOKEN: "+ resources.Properties.X_API_TOKEN})
    Call<ResponseBody> createSupport(
            @Query("accessToken") String accessToken,
            @Part("supportBody") String supportBody,
            @Part("supportType") String supportType,
            @Part List<MultipartBody.Part> supportFiles
    );
}