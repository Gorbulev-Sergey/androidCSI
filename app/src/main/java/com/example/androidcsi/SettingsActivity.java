package com.example.androidcsi;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;

import com.takisoft.preferencex.PreferenceFragmentCompat;

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
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        EditTextPreference preferenceRatingCount;
        EditTextPreference preferenceTalonCount;

        @Override
        public void onCreatePreferencesFix(Bundle savedInstanceState, String rootKey) {
            getPreferenceManager().setSharedPreferencesName("my_preferences");
            addPreferencesFromResource(R.xml.my_preferences);

            preferenceRatingCount = findPreference("ratingCount");
            preferenceTalonCount = findPreference("talonCount");
        }

        @Override
        public void onDisplayPreferenceDialog(Preference preference) {
            preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(@NonNull Preference preference) {
                    if(preference==preferenceRatingCount && ((EditTextPreference)preference).getText()==null){
                        ((EditTextPreference)preference).setText("100");
                    }
                    if(preference==preferenceTalonCount && ((EditTextPreference)preference).getText()==null){
                        ((EditTextPreference)preference).setText("150");
                    }
                    return false;
                }
            });
            super.onDisplayPreferenceDialog(preference);
        }
    }
}