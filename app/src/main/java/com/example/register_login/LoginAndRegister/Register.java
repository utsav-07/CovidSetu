package com.example.register_login.LoginAndRegister;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.register_login.R;
import com.example.register_login.dataStorage.SessionManager;

import java.util.HashMap;
import java.util.Map;

public class    Register extends AppCompatActivity {


    private EditText etName , etEmail , etPassword , etReenterPassword , etnumber;
    private TextView tvStatus;
    private Button btnRegister;

    SessionManager session;

   // private String URL = "http://192.168.43.165/Login_Register/register.php";
   private String URL = "https://hippocratic-dabs.000webhostapp.com/register.php";
    private String name,email,password,reenterPassword, number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        session = new SessionManager(getApplicationContext());
        setContentView(R.layout.activity_register);
        etName = findViewById(R.id.etName);
         etEmail= findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etReenterPassword = findViewById(R.id.etReenterPassword);
        tvStatus = findViewById(R.id.tvStatus);
        btnRegister = findViewById(R.id.button);
    etnumber = findViewById(R.id.etnumbr);


        name = email = password = reenterPassword =  number = "";

    }

    public void save(View view){
       // spinner.setVisibility(View.VISIBLE);
        //session.clerData();
        Toast.makeText(getApplicationContext(), "click", Toast.LENGTH_SHORT).show();
        name = etName.getText().toString().trim();
        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        reenterPassword = etReenterPassword.getText().toString().trim();
        number = etnumber.getText().toString().trim();
        session.insertdata(name , number);
        if(!password.equals(reenterPassword)){
            Toast.makeText(this, "Password Mismatch", Toast.LENGTH_SHORT).show();
        }
        else if(!name.equals("") && !email.equals("") && !password.equals("")){


            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {

                       tvStatus.setText("Successfully registered .");

                       btnRegister.setClickable(false);
                    } else if (response.equals("failuer")) {
                        tvStatus.setText("something went wrong!");
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();

                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String , String> data = new HashMap<>();
                    data.put("name", name);
                    data.put("email", email);
                    data.put("password", password);
                    //spinner.setVisibility(View.GONE);
                    tvStatus.setText("Successfully registered . go to login page");
                    btnRegister.setClickable(false);
                    return data;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
    }

    public void login(View view){
        Intent intent = new Intent(this , MainActivity.class);
        startActivity(intent);
        finish();
    }
}