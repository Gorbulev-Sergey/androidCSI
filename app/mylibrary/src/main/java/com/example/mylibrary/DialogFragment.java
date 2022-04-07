package com.example.mylibrary;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DialogFragment extends AppCompatDialogFragment {
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getLayoutInflater().inflate(R.layout.dialog, null);
        ((EditText) view.findViewById(R.id.editText)).setText(getArguments().getInt("value") == 0 ? "0" : Integer.toString(getArguments().getInt("value")));
        view.findViewById(R.id.editText).requestFocus();
        builder.setTitle(getArguments().getString("title").isEmpty() ? "Задать значение" : getArguments().getString("title"))
                .setView(view)
                .setPositiveButton("Ok", (DialogInterface dialog, int i) -> {
                    Toast.makeText(getActivity(), "Значение изменено", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Отмена", (DialogInterface dialog, int i) -> {
                    dialog.cancel();
                })
                .setCancelable(true);
        return builder.create();
    }
}


