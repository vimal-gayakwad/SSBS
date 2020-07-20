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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

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
        Map<String, Object> user = new HashMap<>();
        user.put("first", "Ada");
        user.put("last", "Lovelace");
        user.put("born", 1815);

// Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        Toast.makeText(SignInActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignInActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });

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
            //startActivity(intent);
        }

    }

    public void signUp(View view) {
        intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intent);
    }
}