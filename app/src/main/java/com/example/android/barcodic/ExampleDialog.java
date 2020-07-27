package com.example.android.barcodic;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ExampleDialog extends AppCompatDialogFragment {
//    private RequestQueue mQueue;
//    MainActivity MainActivity = new MainActivity();
//    String var = MainActivity.getVariable();
    public static ExampleDialog newInstance(String msg1, String msg2) {
                ExampleDialog fragment = new ExampleDialog();
                Bundle bundle = new Bundle();
                bundle.putString("msg1", msg1);
                bundle.putString("msg2", msg2);
                fragment.setArguments(bundle);
                return fragment;
            }
//    String price = "";
//    String currency = "L.L.";
//    String english_name = "";
//    String arabic_name = "";
//    String barcode =getArguments().getString("msg");
//    mQueue = Volley.newRequestQueue(ExampleDialog.this);


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

//        final MainActivity main = (MainActivity) getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getArguments().getString("msg1"))
                .setMessage(getArguments().getString("msg2"))
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        return builder.create();

    }

}
