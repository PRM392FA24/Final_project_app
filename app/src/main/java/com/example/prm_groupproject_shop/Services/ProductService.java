package com.example.prm_groupproject_shop.Services;

import com.example.prm_groupproject_shop.DTOs.APIResponse;
import com.example.prm_groupproject_shop.Model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ProductService {
    @GET("ProductControl/All")
    Call<APIResponse<List<Product>>> getAllProducts();

    @GET("ProductControl/{ProductID}")
    Call<APIResponse<Product>> getProductById(@Path("ProductID") String productId);
}