package com.example.prm_groupproject_shop.Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prm_groupproject_shop.DTOs.APIResponse;
import com.example.prm_groupproject_shop.Factory.APIClient;
import com.example.prm_groupproject_shop.Model.Product;
import com.example.prm_groupproject_shop.R;
import com.example.prm_groupproject_shop.Services.ProductService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends AppCompatActivity {
    private TextView tvProductName, tvProdutDesription, tvProductPrice, tvProductQuantity;
    private String productId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        tvProductName = findViewById(R.id.tvProductName);
        tvProdutDesription = findViewById(R.id.tvProdutDesription);
        tvProductPrice = findViewById(R.id.tvProductPrice);
        tvProductQuantity = findViewById(R.id.tvProductQuantity);

        productId = getIntent().getStringExtra("PRODUCT_ID");

        if (productId != null){
            loadProductDetails(productId);
        }
    }

    private void loadProductDetails(String productId) {
        ProductService productService = APIClient.getClient().create(ProductService.class);
        Call<APIResponse<Product>> call = productService.getProductById(productId);
        call.enqueue(new Callback<APIResponse<Product>>() {
            @Override
            public void onResponse(Call<APIResponse<Product>> call, Response<APIResponse<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    APIResponse<Product> apiResponse = response.body();
//                    Product product = ProductMapper.fromDto(productDTO);

                    if (apiResponse != null && apiResponse.get_data() != null) {
                        Product product = apiResponse.get_data();
                        tvProductName.setText(product.getProductName());
                        tvProdutDesription.setText(product.getProdutDesription());
                        tvProductPrice.setText(String.valueOf(product.getPrice()));
                        tvProductQuantity.setText(String.valueOf(product.getQuantity()));
                    }


                } else {
                    Toast.makeText(ProductDetailsActivity.this,
                            "Product not found",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<APIResponse<Product>> call, Throwable t) {
                Toast.makeText(ProductDetailsActivity.this,
                        "Error: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}


