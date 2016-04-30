package com.example.abdulla.hotel.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.abdulla.hotel.R;
import com.example.abdulla.hotel.adapter.CartAdapter;
import com.example.abdulla.hotel.adapter.ItemsAdapter;
import com.example.abdulla.hotel.helper.Cart;
import com.example.abdulla.hotel.helper.DividerItemDecoration;
import com.example.abdulla.hotel.internet.AppController;
import com.example.abdulla.hotel.model.CartItem;
import com.example.abdulla.hotel.model.Item;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartActivity extends AppCompatActivity {

    String TAG = CartActivity.class.getSimpleName();
    Cart cart = Cart.getInstance();
    TextView carttext;
    private ArrayList<CartItem> itemsList = null;
    private RecyclerView recyclerView;
    private CartAdapter mAdapter;
    String url = "http://192.168.0.102:8080/cart";
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = this;

        if(cart.getItems().size() == 0){
            Log.d(TAG,"No items in cart");
        }else{

            recyclerView = (RecyclerView) findViewById(R.id.recycler_view_cart);
            recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

            mAdapter = new CartAdapter(cart.getItems());
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getBaseContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(mAdapter);


        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Code to do the services call
                Log.d(TAG,"In On click 1");
                final ProgressDialog pDialog = new ProgressDialog(context);
                pDialog.setMessage("Loading...");
                pDialog.show();
                Log.d(TAG,"In On click 2");
                ArrayList<String> cartDetails = new ArrayList<String>();
                for (CartItem cartItem:cart.getItems()){
                    ObjectMapper mapper = new ObjectMapper();
                    String jsonInString = null;
                    try {
                        jsonInString = mapper.writeValueAsString(cartItem);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    cartDetails.add(jsonInString);
                }
                Map<String, String> params = new HashMap<String, String>();
                Log.d(TAG, cartDetails.toString());
                params.put("cart", cartDetails.toString());
                JsonObjectRequest req = new JsonObjectRequest(url, new JSONObject(params),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                pDialog.hide();
                                try {
                                    VolleyLog.v("Response:%n %s", response.toString(4));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.e("Error: ", error.getMessage());
                        pDialog.hide();
                    }
                });
                AppController.getInstance().addToRequestQueue(req);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
