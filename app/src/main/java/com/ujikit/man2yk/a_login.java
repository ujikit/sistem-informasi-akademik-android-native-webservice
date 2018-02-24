package com.ujikit.man2yk;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ujikit on 12/02/18.
 */

public class a_login extends AppCompatActivity {

    //Defining views
    private EditText edt_username;
    private EditText edt_password;
    private Context context;
    private Button btn_login;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_login);
        context = a_login.this;

        //Initializing views
        pDialog = new ProgressDialog(context);
        edt_username = (EditText) findViewById(R.id.edt_username);
        edt_password = (EditText) findViewById(R.id.edt_password);
        btn_login    = (Button) findViewById(R.id.btn_login);

        //aksi ketika tombol 'login' diklik
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });
    }

    private void Login(){
        String url = "http://192.168.43.148/_man2/backend/api2/login.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("Berhasil")){
                    Intent i = new Intent(a_login.this, b_home.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Gagal Login!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error : "+error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("nis_siswa", edt_username.getText().toString().trim());
                params.put("password_siswa", edt_password.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
