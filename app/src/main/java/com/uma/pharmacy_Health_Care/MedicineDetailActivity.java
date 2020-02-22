package com.uma.pharmacy_Health_Care;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import API.API;
import adapter.ItemsAdapter;
import model.Items;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import reusable.Reusable;

public class MedicineDetailActivity extends AppCompatActivity {
    private Button buttonback;
    private RecyclerView rvItems;
    List<Items> itemList = new ArrayList<>();
    String selectedName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_detail);

        rvItems= findViewById(R.id.mobileDetailRv);
        buttonback = findViewById(R.id.btnbacks);
        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedicineDetailActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });

        Bundle bundle = getIntent().getExtras();
        selectedName = bundle.getString("selectedName");

        API Api = Reusable.getInstance().create(API.class);

        Call<List<Items>> listCall = Api.getAllItems();

        listCall.enqueue(new Callback<List<Items>>() {
            @Override
            public void onResponse(Call<List<Items>> call, Response<List<Items>> response) {

                generateList(response.body());

            }

            @Override
            public void onFailure(Call<List<Items>> call, Throwable t) {
                Toast.makeText(MedicineDetailActivity.this, "Error : "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private void generateList( List<Items> body) {
        List<Items> itemList = body;
        List<Items> itemsArrayList = new ArrayList<>();

        for (Items item: itemList){
            String image= item.getImageName();

            if ( item.getName().equals(selectedName) ){
                itemsArrayList.add(new Items(item.getName(), item.getModule(), item.getImageName(),item.getPrice()));
            }

        }
        ItemsAdapter itemAdapter = new ItemsAdapter(this, itemsArrayList);
        rvItems.setAdapter(itemAdapter);
        rvItems.setLayoutManager(new GridLayoutManager(this, 1));
    }
}
