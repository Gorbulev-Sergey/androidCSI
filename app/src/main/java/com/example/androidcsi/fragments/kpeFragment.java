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

import com.example.androidcsi.R;

public class kpeFragment extends Fragment {
    View view;
    NumberPicker pickerGreenTalon;
    NumberPicker pickerRedTalon;
    TextView textResult;
    SharedPreferences preferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        preferences = this.getActivity().getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        view = inflater.inflate(R.layout.fragment_kpe, container, false);
        textResult = view.findViewById(R.id.textKPE);

        pickerGreenTalon = view.findViewById(R.id.picker_high_rating);
        pickerGreenTalon.setMinValue(0);
        pickerGreenTalon.setMaxValue(Integer.parseInt(preferences.getString("talonCount", "150")));
        pickerRedTalon = view.findViewById(R.id.picker_low_rating);
        pickerRedTalon.setMinValue(0);
        pickerRedTalon.setMaxValue(Integer.parseInt(preferences.getString("talonCount", "150")));

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

    @Override
    public void onResume() {
        pickerGreenTalon.setMaxValue(Integer.parseInt(preferences.getString("talonCount", "150")));
        pickerRedTalon.setMaxValue(Integer.parseInt(preferences.getString("talonCount", "150")));
        super.onResume();
    }

    private int getResult(double GreenTalons, double RedTalons) {
        if ((GreenTalons + RedTalons) != 0) {
            double result = 100 * GreenTalons / (GreenTalons + RedTalons);
            return (int) Math.round(result);
        } else return 0;
    }
}