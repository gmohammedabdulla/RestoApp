package com.example.abdulla.hotel.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by 8422 on 29/04/16.
 */
public class ItemsMap implements Serializable {
    Map<String,ArrayList<Item>> itemsData;
    String TAG = ItemsMap.class.getSimpleName();

    public ItemsMap() {
    }

    public ItemsMap(JSONObject object) throws JSONException {
        itemsData = new HashMap<String,ArrayList<Item>>();
        Iterator<String> keysItr = object.keys();
        while(keysItr.hasNext()) {
            String key = keysItr.next();

            JSONArray array = null;
            array = (JSONArray)object.get(key);

            ArrayList<Item> list = new ArrayList<Item>();
            for(int i = 0; i < array.length(); i++) {
                JSONObject value;
                value = (JSONObject)array.get(i);
                //Log.d(TAG,value.get("id").toString()+ value.get("price").toString()+value.get("name").toString()+value.get("description").toString());
                Item item = new Item(value.get("id").toString(), value.get("price").toString(),value.get("name").toString(),value.get("description").toString());
                list.add(item);
            }
            itemsData.put(key,list);
        }

    }

    public Map<String, ArrayList<Item>> getItemsData() {
        return itemsData;
    }

    public void setItemsData(Map<String, ArrayList<Item>> itemsData) {
        this.itemsData = itemsData;
    }

    public Set<String> getCourses(){
        return itemsData.keySet();
    }

    public ArrayList<Item> getMenuList(String key){
        return itemsData.get(key);
    }
}
