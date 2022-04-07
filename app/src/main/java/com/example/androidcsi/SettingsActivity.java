package com.example.androidcsi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.example.androidcsi.fragments.SettingsFragment;

public class SettingsActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        toolbar = findViewById(R.id.toolbarSettings);
        toolbar.setTitle("Настройки");
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }

        getSharedPreferences("my_preferences", MODE_PRIVATE)
                .registerOnSharedPreferenceChangeListener((sharedPreferences, s) -> {
                    if (sharedPreferences.getString(s, "0").isEmpty()) {
                        sharedPreferences.edit().putInt("ratingCount", 100).apply();
                        sharedPreferences.edit().putInt("talonCount", 150).apply();
                    }
                });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}