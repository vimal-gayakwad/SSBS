package com.example.ssbs.DoctorUI.status;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.Switch;
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
    //<---------------------instance declaration--------------------->//
    private FirebaseDatabase database = FirebaseDatabase.getInstance();//firebase  Database Reference
    private DatabaseReference firbaseTempRef = database.getReference("temp");//Realtime database Temperature Reference
    private DatabaseReference firbaseHumidityRef = database.getReference("Humidity");//Realtime database Humidity  Reference
    private DatabaseReference firebaseTimerRef = database.getReference("Timer");
    private DatabaseReference firebaseStatus = database.getReference("LED_STATUS");
    private ProgressBar mProgressBarTemp;//Progressvar for temperature
    private TextView textTemp;//text for temperature
    private TextView textHumidity;//text for Humidity
    private ProgressBar mProgressBarHumidity;
    private NumberPicker numberPicker_minute, numberPicker_second;//number pickers for time set in minutes and seconds
    private TextView texttimer;//text for display timer value
    private Switch aSwitch;//to turn steambath on and off

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_status, container, false);


        LayoutInflater Linflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(
                R.layout.fragment_status, null);

        //<---------------------initialization--------------------->//
        mProgressBarTemp = root.findViewById(R.id.progress_temp);
        mProgressBarHumidity = root.findViewById(R.id.progress_humidity);
        textHumidity = root.findViewById(R.id.text_humidity);
        textTemp = root.findViewById(R.id.text_temp);
        texttimer = root.findViewById(R.id.texttimer);
        aSwitch = root.findViewById(R.id.switch1);
        //numberPicker_minute = (NumberPicker) root.findViewById(R.id.dialog_number_picker_m);
        //numberPicker_second = (NumberPicker) root.findViewById(R.id.dialog_number_picker_s);


        //<---------------------set properties for progressbar--------------------->//
        mProgressBarTemp.setMax(100);
        mProgressBarTemp.setMin(0);

        //<---------------------set properties for numberpickers--------------------->//
        //numberPicker_minute.setMinValue(0);// restricted number to minimum value i.e 1
        //numberPicker_minute.setMaxValue(60);// restricked number to maximum value i.e. 31
        //numberPicker_minute.setWrapSelectorWheel(true);

        //numberPicker_second.setMinValue(0);// restricted number to minimum value i.e 1
        //numberPicker_second.setMaxValue(60);// restricked number to maximum value i.e. 31
        //numberPicker_second.setWrapSelectorWheel(true);

//        numberPicker_minute.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
//            @Override
//            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
//                Toast.makeText(getContext(), "value changed" + newVal, Toast.LENGTH_SHORT).show();
//            }
//        });


        firbaseTempRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                long Tempvalue = dataSnapshot.getValue(long.class);
                mProgressBarTemp.setProgress((int) (Tempvalue));
                textTemp.setText(Tempvalue + "  °C");
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }

        });
        firbaseHumidityRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                long Humidityvalue = snapshot.getValue(long.class);
                mProgressBarHumidity.setProgress((int) Humidityvalue);
                textHumidity.setText(Humidityvalue + "  g.m3");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        firebaseTimerRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String TimerValue = snapshot.getValue(String.class);
                texttimer.setText(TimerValue);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    firebaseStatus.setValue(1);
                } else {
                    firebaseStatus.setValue(0);
                }
            }
        });

        return root;
    }
}