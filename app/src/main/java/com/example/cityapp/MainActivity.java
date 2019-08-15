package com.example.cityapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cityapp.adapters.RvAdapter;
import com.example.cityapp.data.City;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private String URLstring = "https://demonuts.com/Demonuts/JsonTest/Tennis/json_parsing.php";
    public static String TAG = MainActivity.class.getSimpleName();
    LinkedList<City> cityLinkedList;
    RvAdapter adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityLinkedList = new LinkedList<>();
         recyclerView = findViewById(R.id.recycler);


        fetchingJSON();
    }

    private void fetchingJSON() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, ">>" + response);
                        parseJson(response);
                        setupRecycler();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);

    }

    private void parseJson(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for(int i=0; i<jsonArray.length();i++){
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                City city = new City(
                        jsonObject1.getString("name"),
                        jsonObject1.getString("country"),
                        jsonObject1.getString("city"),
                        jsonObject1.getString("imgURL")
                );
                cityLinkedList.add(city);
                Log.i(TAG,city.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setupRecycler(){

        adapter = new RvAdapter(this,cityLinkedList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

    }

}
