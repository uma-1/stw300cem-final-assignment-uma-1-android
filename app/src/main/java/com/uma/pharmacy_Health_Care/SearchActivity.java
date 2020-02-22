package com.uma.pharmacy_Health_Care;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import API.API;
import adapter.ItemsAdapter;
import model.Items;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import reusable.Reusable;

public class SearchActivity extends AppCompatActivity  {
    private RecyclerView recyclerView;
    private EditText etSearch;
    private Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        etSearch = findViewById(R.id.etSearch);
        btnSearch = findViewById(R.id.btnSearchButton);
        recyclerView = findViewById(R.id.rvItem);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
    }

    private void loadData() {
        API api = Reusable.getInstance().create(API.class);
        Call<List<Items>> listCall = api.searchitem(etSearch.getText().toString());
        listCall.enqueue(new Callback<List<Items>>() {
            @Override
            public void onResponse(Call<List<Items>> call, Response<List<Items>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(SearchActivity.this,"code:"+response.code(),Toast.LENGTH_SHORT).show();
                }

                List<Items> itemsList = response.body();

                ItemsAdapter itemsAdapter = new ItemsAdapter(SearchActivity.this, itemsList);
                recyclerView.setAdapter(itemsAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));

            }

            @Override
            public void onFailure(Call<List<Items>> call, Throwable t) {
                Toast.makeText(SearchActivity.this, "Error:" +t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.d("error", t.getLocalizedMessage());

            }
        });



    }
}
