package com.example.bluetoothapplication;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Set;

public class BluetoothActivity extends AppCompatActivity {
    private static final int REQUEST_ENABLE_BT = 1;
    private static final String TAG = "BluetoothActivity";

    private BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Discovery has found a device. Get the BluetoothDevice
                // object and its info from the Intent.
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address
                Log.i(TAG, "Bluetooth device found:\nName: " + deviceName + "\nMAC: " + deviceHardwareAddress);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // When no Bluetooth module is found, show pop-up and press to go back to MainActivity.
        if (bluetoothAdapter == null) {
            new AlertDialog.Builder(this)
                    .setMessage(R.string.bluetooth_module_not_found)
                    .setPositiveButton(R.string.go_back, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    })
                    .show();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        // Determines if text on button is either "Bluetooth On" or "Bluetooth Off".
        Button button = findViewById(R.id.toggleBluetooth);
        if (bluetoothAdapter.isEnabled()) {
            button.setText(R.string.bluetooth_turn_off);
        } else {
            button.setText(R.string.bluetooth_turn_on);
        }

        // Register for broadcasts when a device is discovered.
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(receiver, filter);
    }

    public void toggleBluetooth(View v) {
        Button button = findViewById(R.id.toggleBluetooth);
        if (bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.disable();
            button.setText(R.string.bluetooth_turn_on);
        } else {
            Intent enableBt = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBt, REQUEST_ENABLE_BT);
            button.setText(R.string.bluetooth_turn_off);
        }
    }

    public void queryPairedDevices(View view) {
        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

        if (pairedDevices.size() > 0) {
            // There are paired devices. Get the name and address of each paired device.
            for (BluetoothDevice device : pairedDevices) {
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address
                Log.i(TAG, "queryPairedDevices() - \nName: " + deviceName + "\nMAC: " + deviceHardwareAddress);
            }
        }
    }

    public void callStartDiscovery(View view) {
        bluetoothAdapter.startDiscovery();
        Log.i(TAG, "Called: startDiscovery()");
    }

    public void callCancelDiscovery(View view) {
        bluetoothAdapter.cancelDiscovery();
        Log.i(TAG, "Called: cancelDiscovery()");
    }

    public void callIsDiscovering(View view) {
        Log.i(TAG, "Status: isDiscovering > " + bluetoothAdapter.isDiscovering());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Don't forget to unregister the ACTION_FOUND receiver.
        unregisterReceiver(receiver);
    }
}
