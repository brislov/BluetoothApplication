package com.example.bluetoothapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called by INCREMENT button when pressed, increments value besides button */
    public void incrementValue(View v) {
        TextView textView = findViewById(R.id.textView);
        String getValue = (String) textView.getText();
        int value = Integer.parseInt(getValue);
        value++;
        textView.setText(String.valueOf(value));
        Log.i(TAG, "incrementValue() - current value is " + value);
    }

    public void anotherActivity(View v) {
        Intent intent = new Intent(this, AnotherActivity.class);
//        EditText editText = findViewById(R.id.editText2);
//        String message = editText.getText().toString();
        String msg = "hello world";
        intent.putExtra("message", msg);
        startActivity(intent);
    }

    public void btActivity(View v) {
        Intent intent = new Intent(this, BluetoothActivity.class);
        startActivity(intent);
    }
}
