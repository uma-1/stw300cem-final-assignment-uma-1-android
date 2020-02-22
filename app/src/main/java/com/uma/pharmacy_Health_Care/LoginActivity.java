package com.uma.pharmacy_Health_Care;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.uma.pharmacy_Health_Care.Admin.AdminDashboardActivity;


import API.API;
import model.LoginSignupResponse;
import model.Users;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import reusable.Reusable;

public class LoginActivity extends AppCompatActivity {


    private EditText user, pass;

    private Button login, signup;
    private Spinner spinner;
    private boolean isSuccess = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = findViewById(R.id.etLusername);
        pass = findViewById(R.id.etLpassword);

        login = findViewById(R.id.btnlogin);
        signup = findViewById(R.id.btnsignup);

        spinner = findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.usertype,R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddUser();
                usertype();

            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private void usertype() {

        String item = spinner.getSelectedItem().toString();
        if(user.getText().toString().equals("admin")&& pass.getText().toString().equals("admin")&& item.equals("admin")){
            Intent intent = new Intent(LoginActivity.this, AdminDashboardActivity.class);
            startActivity(intent);


        }


    }


    private void AddUser() {


//        final LoginBill bill = new LoginBill(user.getText().toString(), pass.getText().toString());
//        StrictMod.StrictMode();
//
//        if (bill.checkUser()){
//
//            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
//            startActivity(intent);
//            finish();
//        }
//        else {
//            Toast.makeText(LoginActivity.this, "error is found", Toast.LENGTH_SHORT).show();
//        }
//



     API api = Reusable.getInstance().create(API.class);
        String username = user.getText().toString().trim();
     String password = pass.getText().toString().trim();

       Users user = new Users(username, password);
       Call<LoginSignupResponse> userCall = api.loginUser(user);
       userCall.enqueue(new Callback<LoginSignupResponse>() {
           @Override
           public void onResponse(Call<LoginSignupResponse> call, Response<LoginSignupResponse> response) {
               if (!response.isSuccessful()) {

                    Toast.makeText(LoginActivity.this,"username or password incorrect:" , Toast.LENGTH_LONG).show();
                    return;
               }
                else {

                    if (response.body().getSuccess()){
                       Toast.makeText(LoginActivity.this, "Login successfully", Toast.LENGTH_LONG).show();

 //                  Url.Cookie =response.headers().get("Set-cookie");
                       Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                        startActivity(intent);


                    }
                }
            }

            @Override
            public void onFailure(Call<LoginSignupResponse> call, Throwable t) {


                    Toast.makeText(LoginActivity.this, "Error :"+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();

            }
        });
   }


}
