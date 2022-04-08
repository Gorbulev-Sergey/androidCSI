package com.example.mylibrary;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DialogFragment extends AppCompatDialogFragment {
    SharedPreferences preferences;
    PreferenceView preference;

    public DialogFragment(PreferenceView preference) {
        this.preference = preference;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        preferences = getActivity().getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        View view = getLayoutInflater().inflate(R.layout.dialog, null);

        EditText editText = view.findViewById(R.id.editText);
        editText.setText(((TextView) preference.findViewById(R.id.textValue)).getText().toString().isEmpty() ? "0" : ((TextView) preference.findViewById(R.id.textValue)).getText().toString());
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence chrSequence, int i, int i1, int i2) {
                if (chrSequence.toString().isEmpty()) editText.setText("0");
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(((TextView) preference.findViewById(R.id.textTitle)).getText().toString().isEmpty() ? "Задать значение" : ((TextView) preference.findViewById(R.id.textTitle)).getText().toString())
                .setView(view)
                .setPositiveButton("Ok", (DialogInterface dialog, int i) -> {
                    ((TextView) preference.findViewById(R.id.textValue)).setText(Integer.valueOf(editText.getText().toString()).toString());
                    preferences.edit().putString(preference.preferenceKeyName, editText.getText().toString()).apply();
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    Toast.makeText(getActivity(), "Значение изменено", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Отмена", (DialogInterface dialog, int i) -> {
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    dialog.cancel();
                })
                .setCancelable(true);
        return builder.create();
    }
}


