package com.example.android.barcodic;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.AlertDialog;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class ExampleDialog extends AppCompatDialogFragment {
//    MainActivity MainActivity = new MainActivity();
//    String var = MainActivity.getVariable();
    public static ExampleDialog newInstance(String msg) {
                ExampleDialog fragment = new ExampleDialog();
                Bundle bundle = new Bundle();
                bundle.putString("msg", msg);
                fragment.setArguments(bundle);

                return fragment;
            }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String price = "";
        String currency = "";
        String english_name = "blah1";
        String arabic_name = "blah2";
        Log.e("ExampleDialog", getArguments().getString("msg"));
        final MainActivity main = (MainActivity) getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(price + currency)
                .setMessage(getArguments().getString("msg"))
//                .setMessage("Name: " +english_name+"\n"+"Name: "+arabic_name+"\n")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        return builder.create();

    }

}
