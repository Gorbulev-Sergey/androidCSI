package com.example.androidcsi.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;

import com.example.androidcsi.R;
import com.takisoft.preferencex.PreferenceFragmentCompat;

public class SettingsFragment extends PreferenceFragmentCompat {
    EditTextPreference preferenceRatingCount;
    EditTextPreference preferenceTalonCount;

    @Override
    public void onCreatePreferencesFix(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        getPreferenceManager().setSharedPreferencesName("my_preferences");
        addPreferencesFromResource(R.xml.my_preferences);

        preferenceRatingCount = (EditTextPreference) findPreference("ratingCount");
        preferenceTalonCount = findPreference("talonCount");
    }
}
