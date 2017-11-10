package com.uploadfilestoserver;

import com.uploadfilestoserver.RemoteServer.ServerOne;
import okhttp3.OkHttpClient;
import retrofit2.*;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class App {

    private static final String API_BASE_URL = "https://api.github.com/";

    public static void main( String[] args ) {
        System.out.println( "started!" );

        Retrofit retrofit = HttpClient.getServerOneClient(API_BASE_URL);


        // Create a very simple REST adapter which points the GitHub API endpoint.
        ServerOne client =  retrofit.create(ServerOne.class);

        // Fetch a list of the Github repositories.
        Call<String> call =
                client.reposForUser("fs-opensource");

        // Execute the call asynchronously. Get a positive or negative callback.
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                // The network call was a success and we got a response
                System.out.println(response);
                System.out.println(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // the network call was a failure
                t.printStackTrace();
            }
        });



    }
}
