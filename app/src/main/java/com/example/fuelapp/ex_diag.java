package com.example.fuelapp;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class ex_diag extends AppCompatDialogFragment {

    private EditText ts,te;
    private exdiaglist listner;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());
        LayoutInflater inflater =getActivity().getLayoutInflater();
        View view =inflater.inflate(R.layout.dialog,null);
        builder.setView(view)
        .setTitle("Trip Reading")
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        })
        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String start= ts.getText().toString();
                String end= te.getText().toString();
                listner.applytext(start, end);

            }
        });
        ts=view.findViewById(R.id.ts);
        te=view.findViewById(R.id.te);
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
            super.onAttach(context);
        try {
            listner=(exdiaglist) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

    public interface exdiaglist{

        void applytext(String start,String end);
    }
}
