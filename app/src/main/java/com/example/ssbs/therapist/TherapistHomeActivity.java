package com.example.ssbs.therapist;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ssbs.R;

public class TherapistHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapist_home);
        Toast.makeText(this, "Hello therapist", Toast.LENGTH_SHORT).show();
    }
}