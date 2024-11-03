package com.example.prm_groupproject_shop.Repositories;

import com.example.prm_groupproject_shop.Factory.APIClient;
import com.example.prm_groupproject_shop.Services.ProductService;

public class ProductRepository {
    public static ProductService getProductService() {
        return APIClient.getClient().create(ProductService.class);
    }
}
