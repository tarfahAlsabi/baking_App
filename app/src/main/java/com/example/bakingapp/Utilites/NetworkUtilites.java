package com.example.bakingapp.Utilites;


import android.util.Log;

import java.io.IOException;
import java.net.URL;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkUtilites {
    private static final String tag = "newWorkCall";
    private static final String RECIPE_LIST="https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";


    public static void getRecipsListRequest() throws IOException {
        String result= null;
        URL url = new URL(RECIPE_LIST);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        if(response.code() != 200)
        {
            Log.i(tag,"NETWORK connection error");
            return;
        }
        result = response.body().string();
        Log.i(tag,result);
        JSONUtilites.getRecipes(result);

//            client.newCall(request).enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//                    call.cancel();
//                    Log.i(tag,"failed connecting to the URl");
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//                    if(response.code() == 200) {
//                        String result = response.body().toString();
//                        Log.i(tag,result);
//                        JSONUtilites.getRecipes(result);
//                    }else{
//                        call.cancel();
//                        Log.i(tag,"error in the response ,code: "+response.code());
//                    }
//                }
//
//            });

    }
}
