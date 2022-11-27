package apis;

import java.util.List;

import models.Pojo;
import models.Users;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("transactionstats/")
    Call<List<Pojo>>getdata();

    @GET("users/")
    Call<List<Users>>getUsers();
}
