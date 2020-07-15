package com.example.android.barcodic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyboardShortcutGroup;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

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

        // Set a click listener on that View
        single.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent singleIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(singleIntent);
            }
        });
    }
}