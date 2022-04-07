package com.example.mylibrary;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DialogFragment extends AppCompatDialogFragment {
    com.example.mylibrary.DialogInterface dialogInterface;

    public DialogFragment(com.example.mylibrary.DialogInterface dialogInterface){
        this.dialogInterface = dialogInterface;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View viewLayout = getLayoutInflater().inflate(R.layout.dialog, null);
        EditText viewEditText = viewLayout.findViewById(R.id.editText);
        viewEditText.requestFocus();
        ((EditText) viewLayout.findViewById(R.id.editText)).setText(getArguments().getInt("value") == 0 ? "0" : Integer.toString(getArguments().getInt("value")));
        builder.setTitle(getArguments().getString("title").isEmpty() ? "Задать значение" : getArguments().getString("title"))
                .setView(viewLayout)
                .setPositiveButton("Ok", (DialogInterface dialog, int i) -> {
                    dialogInterface.setValue(Integer.valueOf(viewEditText.getText().toString()));
                    Toast.makeText(getActivity(), "Значение изменено", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Отмена", (DialogInterface dialog, int i) -> {
                    dialog.cancel();
                })
                .setCancelable(true);
        return builder.create();
    }
}


