package com.example.prm_groupproject_shop.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prm_groupproject_shop.Adapter.CartSessionManager;
import com.example.prm_groupproject_shop.DTOs.APIResponse;
import com.example.prm_groupproject_shop.Factory.APIClient;
import com.example.prm_groupproject_shop.Model.CartItem;
import com.example.prm_groupproject_shop.Model.Product;
import com.example.prm_groupproject_shop.R;
import com.example.prm_groupproject_shop.Services.ProductService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends AppCompatActivity {
    private TextView tvProductName, tvProductDescription, tvProductPrice, tvProductQuantity;
    private static final String TAG = "ProductDetailsActivity";
    private CartSessionManager cartManager;
    private CartItem currentProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        tvProductName = findViewById(R.id.tvProductName);
        tvProductDescription = findViewById(R.id.tvProdutDesription);
        tvProductPrice = findViewById(R.id.tvProductPrice);
        tvProductQuantity = findViewById(R.id.tvProductQuantity);


        Button btnAddToCart = findViewById(R.id.buttonAddToCart);
        cartManager = new CartSessionManager(this);  // Initialize your CartSessionManager here

        btnAddToCart.setOnClickListener(view -> {
            if (currentProduct != null) {
                cartManager.addToCart(currentProduct);  // Add the product to the cart
                Toast.makeText(ProductDetailsActivity.this, "Product added to cart", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ProductDetailsActivity.this, "Product data is unavailable", Toast.LENGTH_SHORT).show();
            }
        });

        String productId = getIntent().getStringExtra("PRODUCT_ID");
        Log.d(TAG, "Received Product ID: " + productId);

        if (productId != null) {
            loadProductDetails(productId);
        } else {
            Log.e(TAG, "No Product ID received");
            Toast.makeText(this, "Error: No product ID", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    private void loadProductDetails(String productId) {
        ProductService productService = APIClient.getClient().create(ProductService.class);
        Call<APIResponse<Product>> call = productService.getProductById(productId);

        Log.d(TAG, "Making API call for product ID: " + productId);

        call.enqueue(new Callback<APIResponse<Product>>() {
            @Override
            public void onResponse(Call<APIResponse<Product>> call, Response<APIResponse<Product>> response) {
                Log.d(TAG, "Received API response. Success: " + response.isSuccessful());

                if (response.isSuccessful() && response.body() != null) {
                    Product product = response.body().getData();
                    if (product == null) {
                        product = response.body().get_data();
                    }

                    if (product != null) {
                        Log.d(TAG, "Product data received: " + product.getProductName());

                        tvProductName.setText(product.getProductName());
                        tvProductDescription.setText(product.getProdutDesription());
                        tvProductPrice.setText(String.format("%.2f", product.getPrice()));
                        tvProductQuantity.setText(String.valueOf(product.getProductQuantity()));

                        currentProduct = new CartItem(product.getProductId(), product.getProductName(),product.getProdutDesription(), product.getPrice(), 1);  // Set initial quantity to 1

                    } else {
                        Log.e(TAG, "Product data is null in response");
                        Toast.makeText(ProductDetailsActivity.this,
                                "Error: Product data not found",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e(TAG, "API call failed with code: " + response.code());
                    Toast.makeText(ProductDetailsActivity.this,
                            "Error loading product details",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<APIResponse<Product>> call, Throwable t) {
                Log.e(TAG, "API call failed", t);
                Toast.makeText(ProductDetailsActivity.this,
                        "Network error: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }



        });
    }
}


