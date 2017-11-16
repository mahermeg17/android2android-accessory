package de.quandoo.android2androidaccessory;

import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Date;
import java.util.HashMap;

import butterknife.ButterKnife;


public class ConnectActivity extends AppCompatActivity {


    public static final String TAG = "ConnectActivity";
    public static final String DEVICE_EXTRA_KEY = "device";
    private UsbManager mUsbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ButterKnife.bind(this);

        mUsbManager = (UsbManager) getSystemService(Context.USB_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        final HashMap<String, UsbDevice> deviceList = mUsbManager.getDeviceList();

        if (deviceList == null || deviceList.size() == 0) {
            final Intent intent = new Intent(this, InfoActivity.class);
            startActivity(intent);

            finish();
            return;
        }
        MyLog.i(TAG, "--------- star searching for usb devices -----------");
        MyLog.i(TAG, " >>> " + new Date().toString() + " >>> " + deviceList.size());
        if (searchForUsbAccessory(deviceList)) {
            // return;
        }

        for (UsbDevice device : deviceList.values()) {
            initAccessory(device);
        }
        MyLog.i(TAG, "-------------<<<<<>>>>>-------------");
        finish();
    }

    private boolean searchForUsbAccessory(final HashMap<String, UsbDevice> deviceList) {
        for (UsbDevice device : deviceList.values()) {
            MyLog.i(TAG, "running android SDK = " + Build.VERSION.SDK_INT);
            MyLog.i(TAG, "Vendor Id =" + device.getVendorId()
                    + ", Device Id = " + device.getDeviceId()
                    + ", Product Id = " + device.getProductId());
            MyLog.i(TAG, "Product Name = " + device.getDeviceName()
                    + ", Device Protocol = " + device.getDeviceProtocol());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                MyLog.i(TAG, "Manufacturer Name = " + device.getManufacturerName()
                        + ", Serial Number = " + device.getSerialNumber()
                        + ", Product Name = " + device.getProductName()
                        + ", Device Class = " + device.getDeviceClass()
                        + ", Device Subclass = " + device.getDeviceSubclass()
                        + ", Configuration Count = " + device.getConfigurationCount()
                        + ", Interface Count = " + device.getInterfaceCount());
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                MyLog.i(TAG, "Product Name = " + device.getVersion());
            }

            if (isUsbAccessory(device)) {
                final Intent intent = new Intent(this, ChatActivity.class);
                intent.putExtra(DEVICE_EXTRA_KEY, device);
                startActivity(intent);
                finish();
                return true;
            }
        }

        return true;
    }

    /**
     * Android Open Accessory Protocol V2
     * 0x2d00:11520
     * 0x2d01:11521
     * 0x2D02:11522
     * 0x2D03:11523
     * 0x2D04:11524
     * 0x2D05:11525
     * FT232R
     * 0x6001:24577
     *
     * @param device
     * @return if device is accessory
     */
    private boolean isUsbAccessory(final UsbDevice device) {
        if ((device.getProductId() == 0x2d00) || (device.getProductId() == 0x2d01)
                || (device.getProductId() == 0x2d02) || (device.getProductId() == 0x2d03)
                || (device.getProductId() == 0x2d04) || (device.getProductId() == 0x2d05)
                || (device.getProductId() == 0x6001)) {
            MyLog.i(TAG, "isUsbAccessory >>> true");
            return true;
        }
        MyLog.i(TAG, "isUsbAccessory >>> false");
        return false;
    }

    private boolean initAccessory(final UsbDevice device) {
        MyLog.i(TAG, "try to init Accessory");
        final UsbDeviceConnection connection = mUsbManager.openDevice(device);

        if (connection == null) {
            MyLog.w(TAG, "connection is null");
            return false;
        }
        MyLog.i(TAG, "Control Transfer");
        initStringControlTransfer(connection, 0, "quandoo"); // MANUFACTURER
        initStringControlTransfer(connection, 1, "Android2AndroidAccessory"); // MODEL
        initStringControlTransfer(connection, 2, "showcasing android2android USB communication"); // DESCRIPTION
        initStringControlTransfer(connection, 3, "0.1"); // VERSION
        initStringControlTransfer(connection, 4, "http://quandoo.de"); // URI
        initStringControlTransfer(connection, 5, "42"); // SERIAL

        connection.controlTransfer(0x40, 53, 0, 0, new byte[]{}, 0, Constants.USB_TIMEOUT_IN_MS);

        connection.close();
        MyLog.i(TAG, "connection close");
        return true;
    }

    private void initStringControlTransfer(final UsbDeviceConnection deviceConnection,
                                           final int index,
                                           final String string) {
        deviceConnection.controlTransfer(0x40, 52, 0, index, string.getBytes(), string.length(), Constants.USB_TIMEOUT_IN_MS);
    }
}
