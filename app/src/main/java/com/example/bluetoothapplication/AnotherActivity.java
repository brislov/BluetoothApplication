package com.example.bluetoothapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AnotherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);
        Intent intent = getIntent();
        String message = intent.getStringExtra("message");
        TextView textView = findViewById(R.id.textView2);
        textView.setText(message);
    }
}
