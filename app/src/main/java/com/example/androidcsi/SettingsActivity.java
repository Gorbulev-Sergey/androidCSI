package com.example.androidcsi;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mylibrary.DialogFragment;
import com.example.mylibrary.PreferenceView;

public class SettingsActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        toolbar = findViewById(R.id.toolbarSettings);
        toolbar.setTitle("Настройки");
        setSupportActionBar(toolbar);

        getSharedPreferences("my_preferences", MODE_PRIVATE)
                .registerOnSharedPreferenceChangeListener((sharedPreferences, s) -> {
                    if (sharedPreferences.getString(s, "0").isEmpty()) {
                        sharedPreferences.edit().putInt("ratingCount", 100).apply();
                        sharedPreferences.edit().putInt("talonCount", 150).apply();
                    }
                });

        LinearLayout container = findViewById(R.id.containerSettings);
        PreferenceView preferenceCountRating = new PreferenceView(this) {{
            ((TextView) getTextTitle()).setText("Количество оценок");
            ((TextView) getTextSubtitle()).setText("Максимальное количество оценок");
            ((TextView) getTextValue()).setText("100");
        }};
        PreferenceView preferenceCountTalons = new PreferenceView(this) {{
            ((TextView) getTextTitle()).setText("Количество талонов");
            ((TextView) getTextSubtitle()).setText("Максимальное количество талонов");
            ((TextView) getTextValue()).setText("150");
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