package com.example.ihe.acmestudy.View;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.example.ihe.acmestudy.R;

public class PSIDialogFragment extends DialogFragment {
    String dialogTitle="";
    String dialogMes;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle(dialogTitle);
        builder.setMessage(dialogMes);
        builder.setNeutralButton(R.string.app_name, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(),"you have clicked it",Toast.LENGTH_LONG).show();

            }
        });
        return builder.create();
    }
}
