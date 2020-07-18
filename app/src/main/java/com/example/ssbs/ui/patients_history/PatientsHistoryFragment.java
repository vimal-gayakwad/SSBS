package com.example.ssbs.ui.patients_history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.ssbs.R;

public class PatientsHistoryFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_patients_history, container, false);
        final TextView textView = root.findViewById(R.id.text_appointments);

        textView.setText("PatientsHistory");
        return root;
    }
}