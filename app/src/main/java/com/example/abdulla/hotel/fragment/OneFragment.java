package com.example.abdulla.hotel.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abdulla.hotel.R;
import com.example.abdulla.hotel.adapter.ItemsAdapter;
import com.example.abdulla.hotel.helper.DividerItemDecoration;
import com.example.abdulla.hotel.model.Item;
import com.example.abdulla.hotel.model.ItemsMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 8422 on 29/04/16.
 */
public class OneFragment extends Fragment {
    private ArrayList<Item> itemsList = null;
    private RecyclerView recyclerView;
    private ItemsAdapter mAdapter;

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        /*textView = (TextView) view.findViewById(R.id.textview);
        Bundle bundle = getArguments();
        String data = bundle.getString("key");
        textView.setText(data);*/

        Bundle bundle = getArguments();
        itemsList = (ArrayList<Item>)bundle.get("data");

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), LinearLayoutManager.VERTICAL));


        mAdapter = new ItemsAdapter(itemsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

}
