package com.example.android.barcodic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyboardShortcutGroup;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    public static String myVariable;
    private RequestQueue mQueue;
    public static String english_name;
    public static String arabic_name;
    public static String price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the View that shows the keyboard scan
        ImageView keyboard = (ImageView) findViewById(R.id.keyboard_scan);

        // Set a click listener on that View
        keyboard.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent keyboardIntent = new Intent(MainActivity.this, keyboardActivity.class);
                startActivity(keyboardIntent);
            }
        });
        // Find the View that redirect to camera to scan barcode
        ImageView single = (ImageView) findViewById(R.id.single_scan);
        //added for the volley library

        single.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                IntentIntegrator scanner = new IntentIntegrator(MainActivity.this);
                scanner.initiateScan();
                jsonParse();
                openDialog();

            }
        });
    }
    private void jsonParse() {
        mQueue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.10:8000/api/items-barcode/"+myVariable;
//        String url = "http://192.168.1.255:8000/api/items-barcode/"+myVariable;
//        String url = "http://172.17.0.1:8000/api/items-barcode/"+myVariable;
//        String url = "http://10.0.2.2:8000/api/items-barcode/"+myVariable;
//        String url = "http://127.0.0.1:8000/api/items-barcode/"+myVariable;

        Log.e("fetchedUrl", url);
        Log.e("jsonParseFetched1",english_name+arabic_name+price);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray jsonArray = response.getJSONArray("data");
                        JSONObject item = jsonArray.getJSONObject(0);
                            english_name = item.getString("english_name");
                            arabic_name = item.getString("arabic_name");
                            price = item.getString("price");
                            Log.e("jsonParseFetched2",english_name+arabic_name+price);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("catchErrorException", "catched Error what the fuck");
                    }
                }, error -> error.printStackTrace());
        mQueue.add(request);
}

    public void openDialog() {
//        ExampleDialog exampleDialog = new ExampleDialog();
        ExampleDialog exampleDialog = new ExampleDialog().newInstance(myVariable);
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        myVariable = result.getContents();

        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}