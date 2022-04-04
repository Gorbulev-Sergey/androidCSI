package com.example.androidcsi.fragments;

import android.content.Context;
import android.content.SharedPreferences;
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
    SharedPreferences preferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        preferences = this.getActivity().getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        view = inflater.inflate(R.layout.fragment_high_rating, container, false);
        textResult = view.findViewById(R.id.textResult);

        pickerLowRating = view.findViewById(R.id.picker_low_rating);
        pickerLowRating.setMinValue(0);
        pickerLowRating.setMaxValue(Integer.parseInt(preferences.getString("ratingCount", "100")));
        pickerCSI = view.findViewById(R.id.picker_CSI);
        pickerCSI.setMinValue(0);
        pickerCSI.setMaxValue(100);

        pickerLowRating.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                textResult.setText(Integer.toString(getResult(pickerCSI.getValue(), pickerLowRating.getValue())));
            }
        });

        pickerCSI.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                textResult.setText(Integer.toString(getResult(pickerCSI.getValue(), pickerLowRating.getValue())));
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        pickerLowRating.setMaxValue(Integer.parseInt(preferences.getString("ratingCount", "100")));
        super.onResume();
    }

    private int getResult(double CSI, double LowRating) {
        if ((100 - CSI) != 0) {
            double result = LowRating * (100 + CSI) / (100 - CSI);
            return (int) Math.round(result);
        } else return 0;
    }
}