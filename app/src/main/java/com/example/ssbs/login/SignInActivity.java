package com.example.ssbs.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ssbs.R;
import com.example.ssbs.doctor.DoctorHomeNav;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class SignInActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText edUserID, edPassword;
    private Intent intent;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

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


            db.collection("Users")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {

                                String username = edUserID.getText().toString(),
                                        password = edPassword.getText().toString();
                                String DBusername, DBPassword, usertype;

                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    // Toast.makeText(SignInActivity.this, ""+document.getData(), Toast.LENGTH_LONG).show();
                                    if (document.getString("username").equals(username) && document.getString("password").equals(password)) {
                                        usertype = document.getString("utype");

                                        if (usertype.equals("doctor")) {
                                            intent = new Intent(getApplicationContext(), DoctorHomeNav.class);
                                            startActivity(intent);
                                        } else if (usertype.equals("therapist")) {
                                            //TODO ADD THERAPIST ACTIVITY
                                            Toast.makeText(SignInActivity.this, "Redirect To Therapist", Toast.LENGTH_SHORT).show();
                                        } else if (usertype.equals("patient")) {
                                            //TODO ADD PATIENT ACTIVITY
                                            Toast.makeText(SignInActivity.this, "Redirect To Patient ", Toast.LENGTH_SHORT).show();
                                        }

                                    } else {

                                    }
                                }
                            } else {
                                Toast.makeText(SignInActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }


                        }

                    });
        }
    }

    public void signUp(View view) {
        intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intent);
    }

}