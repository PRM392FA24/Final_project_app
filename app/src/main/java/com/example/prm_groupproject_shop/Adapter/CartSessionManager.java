package com.example.prm_groupproject_shop.Adapter;


import static android.app.ProgressDialog.show;

import android.content.Context;
import android.widget.Toast;

import com.example.prm_groupproject_shop.Model.CartItem;

import java.util.ArrayList;
import java.util.List;

public class CartSessionManager {
    private static List<CartItem> cartItems = new ArrayList<>();
    private Context context;
    private static CartSessionManager instance;

    public CartSessionManager(Context context) {
        this.context = context.getApplicationContext(); // Use application context to avoid memory leaks
    }

    public static synchronized CartSessionManager getInstance(Context context) {
        if (instance == null) {
            instance = new CartSessionManager(context);
        }
        return instance;
    }

    public void addToCart(CartItem item) {
        for (CartItem x : cartItems) {
            if (x.getProductId().equals(item.getProductId())) {
                // Product already exists in cart, increase quantity
                item.increaseQuantity(1);
                Toast.makeText(context, "Increased quantity of " + item.getProductName(), Toast.LENGTH_SHORT).show();
                return;
            }
        }
        // Product not found, add new item
        cartItems.add(item);
        Toast.makeText(context, "Added " + item.getProductName() + " to cart", Toast.LENGTH_SHORT).show();

    }

    public List<CartItem> getCartItems() {
        return new ArrayList<>(cartItems);
    }

    public void clearCart() {
        cartItems.clear();
    }


}
