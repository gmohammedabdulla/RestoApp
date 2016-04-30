package com.example.abdulla.hotel.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.abdulla.hotel.R;
import com.example.abdulla.hotel.helper.Cart;
import com.example.abdulla.hotel.model.CartItem;


import java.util.List;

/**
 * Created by 8422 on 30/04/16.
 */
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    Cart cart = Cart.getInstance();
    private List<CartItem> itemsList;
    String TAG = ItemsAdapter.class.getSimpleName();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,price,description;
        public NumberPicker numberPicker;
        public MyViewHolder(View view) {
            super(view);
            numberPicker = (NumberPicker) view.findViewById(R.id.numberPicker);
            name = (TextView) view.findViewById(R.id.name);
            price = (TextView) view.findViewById(R.id.price);
            description = (TextView) view.findViewById(R.id.description);
        }
    }


    public CartAdapter(List<CartItem> itemsList) {
        this.itemsList = itemsList;
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        CartItem item = itemsList.get(position);
        holder.name.setText(item.getName());
        holder.price.setText("\u20B9" + item.getPrice());
        holder.description.setText(item.getDescription());
        holder.numberPicker.setMinValue(0);
        holder.numberPicker.setMaxValue(10);
        holder.numberPicker.setValue(item.getQuantity());
        holder.numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Log.d(TAG, Integer.toString(position) + " - " + Integer.toString(newVal));
                cart.addItem(itemsList.get(position).getId(), itemsList.get(position), newVal);
                Log.d(TAG, cart.getItems().toString());
            }
        });
    }

}
