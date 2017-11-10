package com.uploadfilestoserver;

import com.uploadfilestoserver.RemoteServer.ServerOne;
import resources.Properties;
import retrofit2.*;

public class App {

    private static final String API_BASE_URL = Properties.API_BASE_URL;
    private static final String X_API_TOKEN = Properties.X_API_TOKEN;
    private static final String ACCESS_TOKEN = Properties.ACCESS_TOKEN;


    public static void main( String[] args ) {
        System.out.println( "started!" );

        Retrofit retrofit = HttpClient.getServerOneClient(API_BASE_URL);


        // Create a very simple REST adapter which points the GitHub API endpoint.
        ServerOne client =  retrofit.create(ServerOne.class);





        // Fetch public ssos
        Call<String> callSsos = client.getPublicSsos(X_API_TOKEN);

        // Execute the call asynchronously. Get a positive or negative callback.
        callSsos.enqueue(new Callback<String>() {
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







        // Fetch profile.
        Call<String> callProfile = client.getprofile(ACCESS_TOKEN, X_API_TOKEN);

        // Execute the call asynchronously. Get a positive or negative callback.
        callProfile.enqueue(new Callback<String>() {
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
