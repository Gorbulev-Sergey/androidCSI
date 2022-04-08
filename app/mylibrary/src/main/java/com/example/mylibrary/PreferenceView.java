package com.example.mylibrary;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PreferenceView extends LinearLayout{
    TextView textTitle;
    TextView textSubtitle;
    TextView textValue;
    public String preferenceKeyName;

    public PreferenceView(Context context, String preferenceKeyName) {
        super(context);
        LayoutInflater layout = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layout.inflate(R.layout.preference, this);

        textTitle = findViewById(R.id.textTitle);
        textSubtitle = findViewById(R.id.textSubtitle);
        textValue = findViewById(R.id.textValue);
        this.preferenceKeyName = preferenceKeyName;

        setOnClickListener((view -> {
            new DialogFragment(this).show(((AppCompatActivity) context).getSupportFragmentManager(), "fragment");
        }));
    }

    public TextView getTextTitle() {
        return textTitle;
    }

    public TextView getTextSubtitle() {
        return textSubtitle;
    }

    public TextView getTextValue() {
        return textValue;
    }
}
