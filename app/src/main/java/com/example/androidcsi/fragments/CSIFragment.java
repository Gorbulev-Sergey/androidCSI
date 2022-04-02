package com.example.androidcsi.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.androidcsi.R;

public class CSIFragment extends Fragment {
    View view;
    NumberPicker pickerHighRating;
    NumberPicker pickerLowRating;
    TextView textResult;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_csi, container, false);
        textResult = view.findViewById(R.id.textCSI);

        pickerHighRating = view.findViewById(R.id.picker_high_rating);
        pickerHighRating.setMinValue(0);
        pickerHighRating.setMaxValue(100);
        pickerLowRating = view.findViewById(R.id.picker_low_rating);
        pickerLowRating.setMinValue(0);
        pickerLowRating.setMaxValue(100);

        pickerHighRating.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                textResult.setText(Integer.toString(getResult(pickerHighRating.getValue(), pickerLowRating.getValue())));
            }
        });

        pickerLowRating.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                textResult.setText(Integer.toString(getResult(pickerHighRating.getValue(), pickerLowRating.getValue())));
            }
        });


        return view;
    }

    private int getResult(double HighRating, double LowRating) {
        if ((HighRating + LowRating) != 0) {
            double result = 100 * (HighRating - LowRating) / (HighRating + LowRating);
            return (int) Math.round(result);
        } else return 0;
    }
}