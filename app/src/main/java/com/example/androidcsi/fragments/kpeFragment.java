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
    NumberPicker pickerGreenTalon;
    NumberPicker pickerRedTalon;
    TextView textResult;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_kpe, container, false);
        textResult = view.findViewById(R.id.textKPE);

        pickerGreenTalon = view.findViewById(R.id.picker_high_rating);
        pickerGreenTalon.setMinValue(0);
        pickerGreenTalon.setMaxValue(100);
        pickerRedTalon = view.findViewById(R.id.picker_low_rating);
        pickerRedTalon.setMinValue(0);
        pickerRedTalon.setMaxValue(100);

        pickerGreenTalon.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                textResult.setText(Integer.toString(getResult(pickerGreenTalon.getValue(), pickerRedTalon.getValue())));
            }
        });

        pickerRedTalon.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                textResult.setText(Integer.toString(getResult(pickerGreenTalon.getValue(), pickerRedTalon.getValue())));
            }
        });


        return view;
    }

    private int getResult(double GreenTalons, double RedTalons) {
        if ((GreenTalons + RedTalons) != 0) {
            double result = 100 * GreenTalons / (GreenTalons + RedTalons);
            return (int) Math.round(result);
        } else return 0;
    }
}