package com.example.android.barcodic;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.AlertDialog;
import android.util.Log;
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
    public static ExampleDialog newInstance(String msg) {
                ExampleDialog fragment = new ExampleDialog();
                Bundle bundle = new Bundle();
                bundle.putString("msg", msg);
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

//        jsonParse();
        final MainActivity main = (MainActivity) getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("")
                .setMessage(getArguments().getString("msg"))
//                .setMessage("Name: " +english_name+"\n"+"Name: "+arabic_name+"\n"+"Barcode: "+barcode)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        return builder.create();

    }
//    private void jsonParse() {
//        String url = "http://192.168.1.35:8000/api/items-barcode/"+barcode;
//
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
//                response -> {
//                    try {
//                        JSONArray jsonArray = response.getJSONArray("data");
//                        JSONObject item = jsonArray.getJSONObject(0);
//                        english_name = item.getString("english_name");
//                        arabic_name = item.getString("arabic_name");
//                        price = item.getString("price");
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }, error -> error.printStackTrace());
//        mQueue.add(request);
//    }

}
