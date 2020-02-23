package com.example.bluetoothapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private final static int REQUEST_ENABLE_BT = 1;
    private static int RESULT_CODE;

    private BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

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
    }

    public void anotherActivity(View v) {
        Intent intent = new Intent(this, AnotherActivity.class);
        EditText editText = findViewById(R.id.editText2);
        String message = editText.getText().toString();
        intent.putExtra("message", message);
        startActivity(intent);
    }

    public void toggleBluetooth(View v) {


        /** Check device has Bluetooth, if not exit function */
        if (bluetoothAdapter == null) {
            System.out.println("Device doesn't support Bluetooth");
            return;
        }
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            onActivityResult(REQUEST_ENABLE_BT, RESULT_CODE, enableBtIntent);
            System.out.println("---------------------------------");
            System.out.println(RESULT_CODE);
        } else {
            bluetoothAdapter.disable();
        }
    }

    public void showPairedDevices(View v) {
        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

        TextView textView = findViewById(R.id.textView4);
        StringBuilder text = new StringBuilder();
        text.append(textView.getText().toString());

        if (pairedDevices.size() > 0) {
            // There are paired devices. Get the name and address of each paired device.
            for (BluetoothDevice device : pairedDevices) {
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address

                text.append("name: " + deviceName + "\nmac: " + deviceHardwareAddress + "\n\n");
            }
        }
        textView.setText(text);
    }


    public void btActivity(View v) {
        Intent intent = new Intent(this, BluetoothActivity.class);
        startActivity(intent);
    }
}
