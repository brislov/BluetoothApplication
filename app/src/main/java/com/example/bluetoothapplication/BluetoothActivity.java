package com.example.bluetoothapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.view.View;

public class BluetoothActivity extends AppCompatActivity {

    private BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        if (btAdapter == null) {
            // show pop up bt doesn't exist
        }
    }

    public void btToggle(View v) {

    }

    public void btQueryPairedDevices(View v) {

    }

    public void btDiscoverDevices(View v) {

    }
}
