package com.example.ssbs.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ssbs.R;

import static java.lang.Integer.valueOf;

/*
 *
 * Creaeted By Vimal
 *
 * */
public class SignUpActivity extends AppCompatActivity {
    //declaration for variable
    private EditText FullName, Email, Contact, Age, UserName, Password;
    private Button SignUp;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //assignment of variables
        FullName = findViewById(R.id.edFullName);
        Email = findViewById(R.id.edEmail);
        Contact = findViewById(R.id.edContact);
        Age = findViewById(R.id.edAge);
        UserName = findViewById(R.id.edUsername);
        Password = findViewById(R.id.edPassword);
        SignUp = findViewById(R.id.btnSignUp);

    }

    //Method for sign up user fired on executed on button  signup
    public void signUp(View view) {
        //Validate for not Empty Inputs
        if (TextUtils.isEmpty(FullName.getText().toString())) {
            FullName.setError("FullName Required");
        }
        if (TextUtils.isEmpty(Age.getText().toString())) {
            Age.setError("Age Required");
        }
        if (TextUtils.isEmpty(Email.getText().toString())) {
            Email.setError("Email Required");
        }
        if (TextUtils.isEmpty(Contact.getText().toString())) {
            Contact.setError("Contact Required");
        }
        if (TextUtils.isEmpty(UserName.getText().toString())) {
            UserName.setError("UserName Required");
        }
        if (TextUtils.isEmpty(Password.getText().toString())) {
            Password.setError("Password Required");
        } else {
            //get Values from input controls
            String fullName, email, userName, password;
            int age, contact;


            fullName = FullName.getText().toString();
            email = Email.getText().toString();
            userName = UserName.getText().toString();
            age = valueOf(Age.getText().toString());
            contact = valueOf(Contact.getText().toString());

            //TODO Firebase Authentication


            //to start login activity
            intent = new Intent(getApplicationContext(), SignInActivity.class);
            startActivity(intent);
        }
    }
}