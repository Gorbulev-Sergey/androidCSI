package com.example.mylibrary;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DialogFragment extends AppCompatDialogFragment {
    PreferenceView preference;

    public DialogFragment(PreferenceView preference) {
        this.preference = preference;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.dialog, null);
        EditText editText = view.findViewById(R.id.editText);
        editText.setText(((TextView) preference.findViewById(R.id.textValue)).getText().toString().isEmpty() ? "0" : ((TextView) preference.findViewById(R.id.textValue)).getText().toString());
        editText.requestFocus();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence chrSequence, int i, int i1, int i2) {
                if(chrSequence.toString().isEmpty()) editText.setText("0");
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(((TextView)preference.findViewById(R.id.textTitle)).getText().toString().isEmpty() ? "Задать значение" : ((TextView)preference.findViewById(R.id.textTitle)).getText().toString())
                .setView(view)
                .setPositiveButton("Ok", (DialogInterface dialog, int i) -> {
                    ((TextView)preference.findViewById(R.id.textValue)).setText(editText.getText());
                    Toast.makeText(getActivity(), "Значение изменено", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Отмена", (DialogInterface dialog, int i) -> {
                    dialog.cancel();
                })
                .setCancelable(true);
        return builder.create();
    }
}


