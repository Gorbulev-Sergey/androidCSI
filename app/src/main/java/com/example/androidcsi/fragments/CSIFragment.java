package com.example.androidcsi.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.androidcsi.R;

import java.util.prefs.Preferences;

public class CSIFragment extends Fragment {
    View view;
    NumberPicker pickerHighRating;
    NumberPicker pickerNeutralRating;
    NumberPicker pickerLowRating;
    TextView textResult;
    SharedPreferences preferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        preferences = this.getActivity().getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        view = inflater.inflate(R.layout.fragment_csi, container, false);
        textResult = view.findViewById(R.id.textCSI);

        pickerHighRating = view.findViewById(R.id.picker_high_rating);
        pickerHighRating.setMinValue(0);
        pickerHighRating.setMaxValue(Integer.parseInt(preferences.getString("ratingCount", "100")));

        pickerNeutralRating = view.findViewById(R.id.picker_neutral_rating);
        pickerNeutralRating.setMinValue(0);
        pickerNeutralRating.setMaxValue(Integer.parseInt(preferences.getString("ratingCount", "100")));

        pickerLowRating = view.findViewById(R.id.picker_low_rating);
        pickerLowRating.setMinValue(0);
        pickerLowRating.setMaxValue(Integer.parseInt(preferences.getString("ratingCount", "100")));

        pickerHighRating.setOnValueChangedListener((numberPicker, i, i1) ->
                textResult.setText(Integer.toString(getResult(pickerHighRating.getValue(), pickerNeutralRating.getValue(), pickerLowRating.getValue()))));

        pickerNeutralRating.setOnValueChangedListener((numberPicker, i, i1) ->
                textResult.setText(Integer.toString(getResult(pickerHighRating.getValue(), pickerNeutralRating.getValue(), pickerLowRating.getValue()))));

        pickerLowRating.setOnValueChangedListener((numberPicker, i, i1) ->
                textResult.setText(Integer.toString(getResult(pickerHighRating.getValue(), pickerNeutralRating.getValue(), pickerLowRating.getValue()))));

        return view;
    }

    @Override
    public void onResume() {
        pickerHighRating.setMaxValue(Integer.parseInt(preferences.getString("ratingCount", "100")));
        pickerNeutralRating.setMaxValue(Integer.parseInt(preferences.getString("ratingCount", "100")));
        pickerLowRating.setMaxValue(Integer.parseInt(preferences.getString("ratingCount", "100")));

        super.onResume();
    }

    private int getResult(double HighRating, double NeutralRating, double LowRating) {
        if ((HighRating + NeutralRating + LowRating) != 0) {
            double csi = 100 * (HighRating - LowRating) / (HighRating + NeutralRating + LowRating);
            return (int) Math.round(csi);
        } else return 0;
    }
}