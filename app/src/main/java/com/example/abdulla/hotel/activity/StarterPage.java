package com.example.abdulla.hotel.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.abdulla.hotel.R;
import com.example.abdulla.hotel.internet.AppController;
import com.example.abdulla.hotel.model.ItemsMap;

import org.json.JSONException;
import org.json.JSONObject;

public class StarterPage extends AppCompatActivity {

    String url;
    String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter_page);


        TAG = StarterPage.class.getSimpleName();
        url = "http://192.168.0.102:8080/menu";

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Welcome to Restaurant App\n Please wait while Loading...");
        pDialog.show();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,"dum",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        pDialog.hide();
                        ItemsMap itemsMap = null;
                        try {
                            itemsMap = new ItemsMap((JSONObject)response.get("data"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(getBaseContext(), ScrollingActivity.class);
                        intent.putExtra("Data",itemsMap);
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.hide();
                    }
                });


        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

}
