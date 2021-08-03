package com.example.fuelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class fuel_price extends AppCompatActivity {
    TextView prc;
    RequestQueue mque;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_price);
        prc=findViewById(R.id.price);
        mque= Volley.newRequestQueue(this);

                sample();



    }
    private void sample()
    {
        String url = "https://fuelprice-api-india.herokuapp.com/price/Tamil-Nadu/CHENNAI/";


        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String s) {

                if(s.length() == 0){
                    Toast.makeText(fuel_price.this, "user not found", Toast.LENGTH_LONG).show();
                }
                else{
                    try {
                        JSONArray jsonarray = new JSONArray(s);
                        JSONObject jsonobject = jsonarray.getJSONObject(0);
                        //Toast.makeText(fuel_price.this, ""+jsonobject.getString("district"), Toast.LENGTH_LONG).show();
                        JSONArray jsonarray1 = new JSONArray(jsonobject.getString("products"));
                        JSONObject jsonObject1 = jsonarray1.getJSONObject(0);

                        Toast.makeText(fuel_price.this, ""+jsonObject1.getString("productPrice"), Toast.LENGTH_LONG).show();

                        prc.setText(jsonObject1.getString("productPrice")+" ₹");


                       /* for(int i=0; i < jsonarray.length(); i++) {
                            JSONObject jsonobject = jsonarray.getJSONObject(i);
                            JSONArray jsonarray1 = new JSONArray(jsonobject);

                        }*/

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("" + volleyError);

            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(fuel_price.this);
        rQueue.add(request);
    }

   /* private void jsonparse() {

        String url="https://fuelprice-api-india.herokuapp.com/price/Tamil-Nadu/CHENNAI/";

        JsonObjectRequest request= new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("response");

                    for(int i=0;i< jsonArray.length();i++){
                        JSONObject products= jsonArray.getJSONObject(i);
                        String productPrice = products.getString("productPrice");
                        prc.append(productPrice+"\n\n");

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mque.add(request);
    }*/
}