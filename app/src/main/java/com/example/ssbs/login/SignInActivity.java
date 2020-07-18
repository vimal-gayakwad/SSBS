package com.example.ssbs.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ssbs.DoctorHomeNav;
import com.example.ssbs.R;

public class SignInActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText edUserID, edPassword;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        btnLogin = findViewById(R.id.btnSignIn);
        edUserID = findViewById(R.id.edUserID);
        edPassword = findViewById(R.id.edPassword);

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void signIn(View view) {

        if (TextUtils.isEmpty(edUserID.getText())) {
            edUserID.setError("User ID Required");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                edUserID.setFocusedByDefault(true);
            }
        }
        if (TextUtils.isEmpty(edPassword.getText())) {
            edPassword.setError("Password Is Required");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                edUserID.setFocusedByDefault(true);
            }
        } else {
            intent = new Intent(getApplicationContext(), DoctorHomeNav.class);
            startActivity(intent);
        }

    }

    public void signUp(View view) {
        intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intent);
    }
}