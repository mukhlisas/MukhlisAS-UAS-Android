package com.example.muslimcyberarmy.mukhlisas_uas_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    private TextView textHasilJSON;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQueue = Volley.newRequestQueue(this);
        textHasilJSON = findViewById(R.id.txtId);
        Button tombolJSON = findViewById(R.id.btnJSON);

        tombolJSON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uraiJSON();
            }
        });
    }
    private void uraiJSON (){
        String link = "https://http://192.168.5.75/nama.json";


        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject data = response.getJSONObject(id);
                    String id = data.getString("id");
                    String nama= data.getString("nama");
                    String asal_daerah = data.getString("asal_daerah");
                    String kamar = data.getString("kamar");

                    textHasilJSON.append("ID = "+id+"\n"+"Nama = "+nama+"\n"+"Asal Daerah = "
                            +asal_daerah+"\n"+"Kamar = "+kamar+"\n\n");

                }catch (JSONException e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }

        });

        mQueue.add(request);

    }

}