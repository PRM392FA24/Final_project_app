package com.example.prm_groupproject_shop.Repositories;

import android.content.Context;
import android.util.Log;

import com.example.prm_groupproject_shop.Factory.APIClient;
import com.example.prm_groupproject_shop.Model.Product;
import com.example.prm_groupproject_shop.Services.ProductService;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductRepository {
    public static ProductService getProductService() {
        return APIClient.getClient().create(ProductService.class);
    }
}
