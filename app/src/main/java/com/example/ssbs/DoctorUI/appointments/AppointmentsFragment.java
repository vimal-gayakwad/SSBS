package com.example.ssbs.DoctorUI.appointments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.ssbs.R;

public class AppointmentsFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_appointments, container, false);
        final TextView textView = root.findViewById(R.id.text_appointments);

        textView.setText("hello");
        return root;
    }
}