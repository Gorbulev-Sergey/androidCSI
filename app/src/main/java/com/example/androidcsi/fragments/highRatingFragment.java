package com.example.androidcsi.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.androidcsi.MainActivity;
import com.example.androidcsi.R;

public class highRatingFragment extends Fragment {
    View view;
    NumberPicker pickerLowRating;
    NumberPicker pickerCSI;
    TextView textResult;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_high_rating, container, false);
        textResult = view.findViewById(R.id.textResult);

        pickerLowRating = view.findViewById(R.id.picker_low_rating);
        pickerLowRating.setMinValue(0);
        pickerLowRating.setMaxValue(200);
        pickerCSI = view.findViewById(R.id.picker_CSI);
        pickerCSI.setMinValue(0);
        pickerCSI.setMaxValue(100);

        pickerLowRating.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                textResult.setText(Integer.toString(getResult(pickerLowRating.getValue(), pickerCSI.getValue())));
            }
        });

        pickerCSI.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                textResult.setText(Integer.toString(getResult(pickerLowRating.getValue(), pickerCSI.getValue())));
            }
        });

        return view;
    }

    private int getResult(int LowRating, int CSI) {
        try {
            return  (LowRating * (100 + CSI)) / (100 - CSI);
        }
        catch (Exception e){}
        return 0;
    }
}