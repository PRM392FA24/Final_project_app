package com.example.prm_groupproject_shop.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm_groupproject_shop.Adapter.ProductAdapter;
import com.example.prm_groupproject_shop.DTOs.APIResponse;
import com.example.prm_groupproject_shop.Model.Product;
import com.example.prm_groupproject_shop.R;
import com.example.prm_groupproject_shop.Repositories.ProductRepository;
import com.example.prm_groupproject_shop.Services.ProductService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewProducts;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    private ProductService productService;
    private Button btnCart,btnMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewProducts = findViewById(R.id.recyclerViewProducts);
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList<>();
        productAdapter = new ProductAdapter(this, productList);
        recyclerViewProducts.setAdapter(productAdapter);

        productService = ProductRepository.getProductService();

        loadProducts();

        btnCart = findViewById(R.id.buttonCart);
        btnCart.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(intent);
        });


        btnMap = findViewById(R.id.btnMap);
        btnMap.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MapActivity.class);
            startActivity(intent);
        });
    }

    private void loadProducts() {
        Call<APIResponse<List<Product>>> call = productService.getAllProducts();

        call.enqueue(new Callback<APIResponse<List<Product>>>() {
            @Override
            public void onResponse(Call<APIResponse<List<Product>>> call,
                                   Response<APIResponse<List<Product>>> response) {
                if (response.isSuccessful()) {
                    APIResponse<List<Product>> apiResponse = response.body();
                    if (apiResponse != null && apiResponse.getData() != null) {
                        productList.clear();
                        productList.addAll(apiResponse.getData());
                        productAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(MainActivity.this,
                                "No products available",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this,
                            "Error: " + response.code(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<APIResponse<List<Product>>> call, Throwable t) {
                // Handle network failure
                Log.e("MainActivity", "Network error: " + t.getMessage(), t);
                Toast.makeText(MainActivity.this,
                        "Network error: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void refreshProducts() {
        loadProducts();
    }
}
