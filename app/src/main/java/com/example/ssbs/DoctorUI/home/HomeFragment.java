package com.example.ssbs.DoctorUI.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.ssbs.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeFragment extends Fragment {
    boolean flag = false;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("LED_STATUS");

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_home, container, false);

        final Switch mSwitch = root.findViewById(R.id.switch1);

        // final TextView textView = root.findViewById(R.id.text_home);


        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (flag == false) {
                    flag = true;
                    setStatus(flag);
                    myRef.setValue("1");
                } else {
                    flag = false;
                    setStatus(flag);
                    myRef.setValue("0");
                }
                //Toast.makeText(getContext(), "" + flag, Toast.LENGTH_SHORT).show();
            }
        });


        return root;
    }

    public void setStatus(boolean flag) {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Toast.makeText(getContext(), "Value is" + value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}