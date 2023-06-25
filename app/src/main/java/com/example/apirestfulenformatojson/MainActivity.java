package com.example.apirestfulenformatojson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apirestfulenformatojson.WebService.Asynchtask;
import com.example.apirestfulenformatojson.WebService.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View viewLista){
        Map<String,String> datos = new HashMap<String,String>();
        WebService ws = new WebService("https://gorest.co.in/public/v1/users", datos, MainActivity.this,
                MainActivity.this);
        ws.execute("GET");
    }


    @Override
    public void processFinish(String result) throws JSONException {
        TextView txtmuestralist=findViewById(R.id.txtmostrarlista);
        String list="";
        JSONObject objectojson=new JSONObject(result);
        JSONArray JSONLista=objectojson.getJSONArray("data");
        for(int i=0; i<JSONLista.length();i++){
            JSONObject mostrarlista=JSONLista.getJSONObject(i);
            list=list +"user: "+mostrarlista.getString("name").toString()+
                    "\r\n\"email: "+mostrarlista.getString("email").toString()+"\r\n";

        }
        txtmuestralist.setText(list);

    }
}