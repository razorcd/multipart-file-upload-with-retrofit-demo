package com.uploadfilestoserver.RemoteServer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ServerOne {
    @GET("v1/external_ssos/search?accessToken=public&action=index&controller=public&locale=en")
    Call<String> getPublicSsos(
            @Header("X-API-TOKEN") String xApiToken
    );

    @GET("v1/clients/profile")
    Call<String> getprofile(
            @Query("accessToken") String accessToken,
            @Header("X-API-TOKEN") String xApiToken
    );
}