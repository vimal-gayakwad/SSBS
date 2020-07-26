package com.example.ssbs.DoctorUI.status;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.ssbs.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StatusFragment extends Fragment {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Temp");
    private ProgressBar mProgressBarTemp;
    private TextView textTemp;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_status, container, false);
        mProgressBarTemp = root.findViewById(R.id.progress_temp);
        textTemp = root.findViewById(R.id.text_temp);
        myRef.setValue("1");
        mProgressBarTemp.setMax(100);
        mProgressBarTemp.setMin(0);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                mProgressBarTemp.setProgress((int) Float.parseFloat(value));
                textTemp.setText(value + "°C");
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
        return root;
    }
}