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


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, link,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("0");

                    for (int i =0; i < jsonArray.length(); i++){

                        JSONObject anggota = jsonArray.getJSONObject(i);

                        String id = anggota.getString("id");
                        String nama = anggota.getString("nama");
                        String asalDaerah = anggota.getString("asal_daerah");
                        String Kamar = anggota.getString("kamar");


                        textHasilJSON.append(id+ ". " + nama + ", "
                                + asalDaerah + ", " + Kamar + " \n\n");
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Error1", Toast.LENGTH_SHORT).show();
                }
               }
     }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            error.printStackTrace();
            Toast.makeText(getApplicationContext(),"Error1", Toast.LENGTH_SHORT).show();

        }
    });
        mQueue.add(request);
}
}
