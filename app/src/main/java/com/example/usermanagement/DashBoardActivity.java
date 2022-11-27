package com.example.usermanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapters.CustomAdapter;
import adapters.recycleadapter;
import apis.ApiInterface;
import apis.Retrofit;
import models.Pojo;
import models.Users;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashBoardActivity extends AppCompatActivity {
    RecyclerView recycle,recycle1;
    private CustomAdapter customAdapter;
    public ArrayList<Users> userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        recycle=findViewById(R.id.rec);
        listingData();
        LinearLayoutManager llm=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recycle.setLayoutManager(llm);
        recycle1=findViewById(R.id.lm);
        listingUsers();
        LinearLayoutManager llv=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recycle1.setLayoutManager(llv);

    }
    ApiInterface apiInterface= Retrofit.getRetrofit().create(ApiInterface.class);
    private void listingUsers() {
        Call<List<Users>> call = apiInterface.getUsers();
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                userList = new ArrayList<>(response.body());
                customAdapter = new CustomAdapter(getApplicationContext(), userList);
                recycle1.setAdapter(customAdapter);
            }
            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failure Api call", Toast.LENGTH_SHORT);
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_item, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_search);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                customAdapter.getFilter().filter(newText);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void listingData() {
        Call<List<Pojo>>listingdata=apiInterface.getdata();
        listingdata.enqueue(new Callback<List<Pojo>>() {
            @Override
            public void onResponse(Call<List<Pojo>> call, Response<List<Pojo>> response) {
                if(response.isSuccessful()){
                    recycleadapter adapter=new recycleadapter(response.body());
                    recycle.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Pojo>> call, Throwable t) {

                Toast.makeText(getApplicationContext(),"Failure Api call", Toast.LENGTH_SHORT);
            }
        });
    }


}
