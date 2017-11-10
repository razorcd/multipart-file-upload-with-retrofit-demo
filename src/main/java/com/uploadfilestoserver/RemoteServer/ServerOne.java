package com.uploadfilestoserver.RemoteServer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ServerOne {
    @GET("/users/{user}/repos")
    Call<String> reposForUser(
            @Path("user") String user
    );
}