package com.example.mylibrary;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PreferenceView extends LinearLayout implements DialogInterface{
    TextView textTitle;
    TextView textSubtitle;
    TextView textValue;

    public PreferenceView(Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.preference, this);

        textTitle = findViewById(R.id.textTitle);
        textSubtitle = findViewById(R.id.textSubtitle);
        textValue = findViewById(R.id.textResult);

        setOnClickListener((view -> {
            Bundle bundle = new Bundle();
            bundle.putString("title", textTitle.getText().toString());
            bundle.putString("subtitle", textSubtitle.getText().toString());
            bundle.putInt("value", Integer.valueOf(textValue.getText().toString()));

            DialogFragment dialog = new DialogFragment(this);
            dialog.setArguments(bundle);
            dialog.show(((AppCompatActivity)context).getSupportFragmentManager(), "fragment");
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

    @Override
    public void setValue(int value) {
        textValue.setText(Integer.toString(value));
    }
}
