package com.example.androidcsi.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.androidcsi.R;

public class kpeFragment extends Fragment {
    View view;
    NumberPicker pickerHighTalon;
    NumberPicker pickerLowTalon;
    TextView textResult;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_kpe, container, false);
        textResult = view.findViewById(R.id.textKPE);

        pickerHighTalon = view.findViewById(R.id.picker_high_rating);
        pickerHighTalon.setMinValue(0);
        pickerHighTalon.setMaxValue(200);
        pickerLowTalon = view.findViewById(R.id.picker_low_rating);
        pickerLowTalon.setMinValue(0);
        pickerLowTalon.setMaxValue(200);

        pickerHighTalon.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                textResult.setText(Integer.toString(getResult(pickerLowTalon.getValue(), pickerHighTalon.getValue())));
            }
        });

        pickerLowTalon.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                textResult.setText(Integer.toString(getResult(pickerLowTalon.getValue(), pickerHighTalon.getValue())));
            }
        });


        return view;
    }

    private int getResult(int LowTalon, int HighTalon) {
        try{
            return (HighTalon*100)%(HighTalon+LowTalon)<.5?(HighTalon*100)/(HighTalon+LowTalon):(HighTalon*100)/(HighTalon+LowTalon)+1;
        }
        catch (Exception e){}
        return 0;

    }
}