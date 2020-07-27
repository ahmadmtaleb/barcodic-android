package com.example.android.barcodic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class keyboardActivity extends AppCompatActivity {
    private RequestQueue mQueue;
    public static String english_name;
    public static String arabic_name;
    public static String price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);
    }
    public void submitOrder(View view) {
        EditText text = (EditText) findViewById(R.id.keyboard_input);
        String barcode = text.getText().toString();
        jsonParse(barcode);
        text.setText("");
    }
    private void jsonParse(String myVar) {
        mQueue = Volley.newRequestQueue(keyboardActivity.this);
        String url = "http://192.168.1.3:8000/api/items-barcode/"+myVar;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray jsonArray = response.getJSONArray("data");
                        JSONObject item = jsonArray.getJSONObject(0);
                        english_name = item.getString("english_name");
                        arabic_name = item.getString("arabic_name");
                        price = item.getString("price");
                        openDialog(myVar, price, english_name, arabic_name);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> error.printStackTrace());
        mQueue.add(request);
    }
    public void openDialog(String barcode, String price, String name1, String name2) {
        String msg1 = price + "  L.L.";
        String msg2 = name2 + "\n" + name1 + "\n" + barcode;
        ExampleDialog exampleDialog = new ExampleDialog()
                .newInstance(msg1, msg2);
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }
}