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
import java.util.Arrays;
import java.util.List;

public class App {

    private static final String API_BASE_URL = Properties.API_BASE_URL;
    private static final String ACCESS_TOKEN = Properties.ACCESS_TOKEN;
    private static final String FILE1 = "src/main/java/resources/dummy1.txt";
    private static final String FILE2 = "src/main/java/resources/dummy2.txt";


    public static void main( String[] args ) throws InterruptedException {
        System.out.println( "App started!" );

//        callGetPublicSsos();
//        Thread.sleep(4000L);
//        System.out.println(".");
//
//        callGetprofile();
//        Thread.sleep(4000L);
//        System.out.println(".");
//
        callCreateSupport();
//        Thread.sleep(4000L);
        System.out.println(".");
    }

    public static void callGetPublicSsos() {
        Retrofit retrofit = HttpClient.getServerOneClient(API_BASE_URL);

        // Create a very simple REST adapter which points the GitHub API endpoint.
        ServerOne client =  retrofit.create(ServerOne.class);


        // Fetch public ssos
        Call<String> callSsos = client.getPublicSsos();

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
    }


    public static void callGetprofile() {
        Retrofit retrofit = HttpClient.getServerOneClient(API_BASE_URL);

        // Create a very simple REST adapter which points the GitHub API endpoint.
        ServerOne client =  retrofit.create(ServerOne.class);


        // Fetch private profile.
        Call<String> callProfile = client.getprofile(ACCESS_TOKEN);

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


    public static void callCreateSupport() {
        // create upload service client
        ServerOne service = ServiceGenerator.createService(ServerOne.class);

        // set some files
        File file1 = FileUtils.getFile(FILE1);
        File file2 = FileUtils.getFile(FILE2);

        // create RequestBody instance from file
        RequestBody requestFile1 = RequestBody.create(MediaType.parse("application/octet-stream"), file1);
        RequestBody requestFile2 = RequestBody.create(MediaType.parse("application/octet-stream"), file2);

        // MultipartBody.Part is used to send also the actual file name
        // ! An empty list would end in a sent email with no attachments.
        List<MultipartBody.Part> supportFiles = Arrays.asList(
                MultipartBody.Part.createFormData("supportFiles", file1.getName(), requestFile1),
                MultipartBody.Part.createFormData("supportFiles", file2.getName(), requestFile2)
        );

//        RequestBody description = RequestBody.create(MultipartBody.FORM, "test from Cristian");

        // create request
        Call<ResponseBody> call = service.createSupport(
                ACCESS_TOKEN, "test from Cristian", "TYPE1", supportFiles);

        //call request async
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                System.out.println(response);
                System.out.println(response.body());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

}
