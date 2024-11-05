package com.example.prm_groupproject_shop.Activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.prm_groupproject_shop.Adapter.CartAdapter;
import com.example.prm_groupproject_shop.Model.CartItem;
import com.example.prm_groupproject_shop.R;
import com.example.prm_groupproject_shop.Adapter.CartSessionManager;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private RecyclerView rvCartItems;
    private TextView tvSubtotal, tvSubtotalAmount, tvTax, tvTaxAmount, tvTotal, tvTotalAmount;
    private Button btnCheckout;
    private CartSessionManager cartSessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        rvCartItems = findViewById(R.id.rvCartItems);
        tvSubtotal = findViewById(R.id.tvSubtotalAmount);
        tvTax = findViewById(R.id.tvTaxAmount);
        tvTotal = findViewById(R.id.tvTotalAmount);
        btnCheckout = findViewById(R.id.btnCheckout);

        cartSessionManager = new CartSessionManager(this);

        setupRecyclerView();
        calculateTotals();

        btnCheckout.setOnClickListener(v -> {
            Toast.makeText(CartActivity.this, "Proceeding to Checkout", Toast.LENGTH_SHORT).show();
            // Add your checkout process here
        });
    }

    private void setupRecyclerView() {
        List<CartItem> cartItems = cartSessionManager.getCartItems();
        CartAdapter cartAdapter = new CartAdapter(cartItems, this);

        rvCartItems.setLayoutManager(new LinearLayoutManager(this));
        rvCartItems.setAdapter(cartAdapter);
    }

    private void calculateTotals() {
        List<CartItem> cartItems = cartSessionManager.getCartItems();

        double subtotal = 0;
        for (CartItem item : cartItems) {
            subtotal += item.getPrice() * item.getQuantity();
        }
        double tax = subtotal * 0.08; // Example tax rate of 8%
        double total = subtotal + tax;

        tvSubtotal.setText(String.format("$%.2f", subtotal));
        tvTax.setText(String.format("$%.2f", tax));
        tvTotal.setText(String.format("$%.2f", total));
    }
}
