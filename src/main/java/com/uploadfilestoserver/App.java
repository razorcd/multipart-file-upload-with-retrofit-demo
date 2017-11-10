package com.uploadfilestoserver;

import com.uploadfilestoserver.RemoteServer.ServerOne;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.apache.commons.io.FileUtils;
import resources.Properties;
import retrofit2.*;

import java.io.File;

public class App {

    private static final String API_BASE_URL = Properties.API_BASE_URL;
    private static final String X_API_TOKEN = Properties.X_API_TOKEN;
    private static final String ACCESS_TOKEN = Properties.ACCESS_TOKEN;


    public static void main( String[] args ) {
        System.out.println( "started!" );

        Retrofit retrofit = HttpClient.getServerOneClient(API_BASE_URL);


        // Create a very simple REST adapter which points the GitHub API endpoint.
        ServerOne client =  retrofit.create(ServerOne.class);

//
//
//
//
//        // Fetch public ssos
//        Call<String> callSsos = client.getPublicSsos(X_API_TOKEN);
//
//        // Execute the call asynchronously. Get a positive or negative callback.
//        callSsos.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                // The network call was a success and we got a response
//                System.out.println(response);
//                System.out.println(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                // the network call was a failure
//                t.printStackTrace();
//            }
//        });
//
//
//
//
//
//
//
//        // Fetch private profile.
//        Call<String> callProfile = client.getprofile(ACCESS_TOKEN, X_API_TOKEN);
//
//        // Execute the call asynchronously. Get a positive or negative callback.
//        callProfile.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                // The network call was a success and we got a response
//                System.out.println(response);
//                System.out.println(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                // the network call was a failure
//                t.printStackTrace();
//            }
//        });


        uploadFile();
    }


    public static void uploadFile() {
        // create upload service client
        ServerOne service = ServiceGenerator.createService(ServerOne.class);

        // use the FileUtils to get the actual file by uri
        File file = FileUtils.getFile("src/main/java/resources/dummy.txt");

        // create RequestBody instance from file
        RequestBody requestFile = RequestBody.create(
                        MediaType.parse("image/png"),
                        file
                    );

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part supportFiles =
                MultipartBody.Part.createFormData(file.getName(), file.getName(), requestFile);

//        RequestBody description = RequestBody.create(MultipartBody.FORM, "test from Cristian");

        // finally, execute the request
        Call<ResponseBody> call = service.createSupport(ACCESS_TOKEN, X_API_TOKEN, "multipart/form-data",
                "test from Cristian", "TYPE1", supportFiles);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                System.out.println(response.body());
                System.out.println(response);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

}
