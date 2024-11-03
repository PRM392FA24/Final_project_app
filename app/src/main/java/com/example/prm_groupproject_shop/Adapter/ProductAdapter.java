package com.example.prm_groupproject_shop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm_groupproject_shop.Model.Product;
import com.example.prm_groupproject_shop.Activity.ProductDetailsActivity;
import com.example.prm_groupproject_shop.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private Context context;
    private List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList != null ? productList : new ArrayList<>();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.tvProductName.setText(product.getProductName());
        holder.tvProdutDesription.setText(product.getProdutDesription());
        holder.tvProductPrice.setText(NumberFormat.getCurrencyInstance(Locale.getDefault()).format(product.getPrice()));

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ProductDetailsActivity.class);
            intent.putExtra("PRODUCT_ID", product.getProductId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void updateProductList(List<Product> newProductList) {
        productList.clear();
        if (newProductList != null) {
            productList.addAll(newProductList);
        }
        notifyDataSetChanged();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView tvProductName, tvProdutDesription, tvProductPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvProdutDesription = itemView.findViewById(R.id.tvProdutDesription);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
        }
    }
}

