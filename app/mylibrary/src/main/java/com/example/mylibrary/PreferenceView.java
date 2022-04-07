package com.example.mylibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PreferenceView extends LinearLayout {
    TextView textTitle;
    TextView textSubtitle;
    TextView textResult;

    public PreferenceView(Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.preference, this);

        textTitle = findViewById(R.id.textTitle);
        textSubtitle = findViewById(R.id.textSubtitle);
        textResult = findViewById(R.id.textResult);

        this.setOnClickListener((View view) -> {

        });
    }

    public TextView getTextTitle() {
        return textTitle;
    }

    public TextView getTextSubtitle() {
        return textSubtitle;
    }

    public TextView getTextResult() {
        return textResult;
    }
}
