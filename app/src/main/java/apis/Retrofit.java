package apis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class Retrofit {
    private static String baseurl="https://630369f20de3cd918b34e39e.mockapi.io/";

     static Gson gson=new GsonBuilder()
             .setLenient()
             .create();
     private static retrofit2.Retrofit retrofit;
     public static retrofit2.Retrofit getRetrofit(){

         if(retrofit==null){

             retrofit=new retrofit2.Retrofit.Builder()
                     .baseUrl(baseurl)
                     .addConverterFactory(GsonConverterFactory.create(gson))
                     .build();
         }
         return retrofit;
     }
}
