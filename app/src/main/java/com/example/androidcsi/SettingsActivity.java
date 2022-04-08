package com.example.androidcsi;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mylibrary.DialogFragment;
import com.example.mylibrary.PreferenceView;

public class SettingsActivity extends AppCompatActivity {
    SharedPreferences preferences;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        preferences = getSharedPreferences("my_preferences", MODE_PRIVATE);

        toolbar = findViewById(R.id.toolbarSettings);
        toolbar.setTitle("Настройки");
        setSupportActionBar(toolbar);

        LinearLayout container = findViewById(R.id.containerSettings);
        PreferenceView preferenceCountRating = new PreferenceView(this, "ratingCount") {{
            ((TextView) getTextTitle()).setText("Количество оценок");
            ((TextView) getTextSubtitle()).setText("Максимальное количество оценок");
            ((TextView) getTextValue()).setText(preferences.getString("ratingCount","100"));
        }};
        PreferenceView preferenceCountTalons = new PreferenceView(this,"talonCount") {{
            ((TextView) getTextTitle()).setText("Количество талонов");
            ((TextView) getTextSubtitle()).setText("Максимальное количество талонов");
            ((TextView) getTextValue()).setText(preferences.getString("talonCount","150"));
        }};
        container.addView(preferenceCountRating);
        container.addView(preferenceCountTalons);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}