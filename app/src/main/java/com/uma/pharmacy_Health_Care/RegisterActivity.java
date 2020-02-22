package com.uma.pharmacy_Health_Care;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import API.API;
import model.Users;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import reusable.Reusable;

public class RegisterActivity extends AppCompatActivity {

    private EditText etfname, etlname, etemail, etphone, etusername, etpasswrod, etconpassword;
    private Button btnregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        etfname = findViewById(R.id.etfname);
        etlname = findViewById(R.id.etlname);
        etemail = findViewById(R.id.etemail);
        etphone = findViewById(R.id.etphone);
        etusername = findViewById(R.id.etusername);
        etpasswrod = findViewById(R.id.etpassword);
        etconpassword = findViewById(R.id.etconfirm);


        btnregister = findViewById(R.id.btnRegister);


        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation()) {
                    Register();


                }
            }
        });


    }

    private void Register() {

        String password = etpasswrod.getText().toString();
        String conpassword = etconpassword.getText().toString();
        String fname = etfname.getText().toString();
        String lname = etlname.getText().toString();
        String email = etemail.getText().toString();
        String phone = etphone.getText().toString();
        String username = etusername.getText().toString();


        if (password.equals(conpassword)) {

            Users user = new Users(fname, lname, email, phone, username, password);

            API api = Reusable.getInstance().create(API.class);
            Call<Void> itemsCall = api.insertUser(user);

            itemsCall.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (!response.isSuccessful()) {

                        Toast.makeText(RegisterActivity.this, "code:" + response.code(), Toast.LENGTH_LONG).show();
                        return;


                    }

                    Toast.makeText(RegisterActivity.this, "sucessfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                    Toast.makeText(RegisterActivity.this, "code:" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                }
            });


        } else {
            etconpassword.setError("Please enter Confirm password");
            etpasswrod.requestFocus();
        }

    }

    private boolean validation() {
    boolean flag = true;
    if (TextUtils.isEmpty(etfname.getText().toString())){

        etfname.setError("Please enter your first name");
        etfname.requestFocus();
        flag = false;
    }
    else if(TextUtils.isEmpty(etlname.getText().toString())){
        etlname.setError("Please enter your last name");
        etlname.requestFocus();
        flag = false;
    }

    else if (TextUtils.isEmpty(etemail.getText().toString())){
        etlname.setError("please enter your email address");
        etlname.requestFocus();
        flag = false;
    }
    else if (TextUtils.isEmpty(etphone.getText().toString())){

        etphone.setError("please enter your phone number");
        etphone.requestFocus();
        flag = false;
    }
    else if (TextUtils.isEmpty(etusername.getText().toString())){
        etusername.setError("please enter your username");
        etusername.requestFocus();
        flag = false;
    }
    else if (TextUtils.isEmpty(etpasswrod.getText().toString())){
        etpasswrod.setError("please enter your password");
        etpasswrod.requestFocus();
        flag = false;
    }

    else if (TextUtils.isEmpty(etconpassword.getText().toString())){
        etconpassword.setError("re write your password");
        etconpassword.requestFocus();
        flag = false;
    }
    return flag;

    }

}
