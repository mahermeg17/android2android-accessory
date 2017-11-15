package de.quandoo.android2androidaccessory;

import android.content.Context;
import android.hardware.usb.UsbAccessory;
import android.hardware.usb.UsbManager;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.util.Log;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public abstract class AccessoryCommunicator {

    private UsbManager usbManager;
    private Context context;
    private Handler sendHandler;
    private ParcelFileDescriptor fileDescriptor;
    private FileInputStream inStream;
    private FileOutputStream outStream;
    private boolean running;
    private static final String TAG = "AccessoryCommunicator";

    public AccessoryCommunicator(final Context context) {
        try {
            this.context = context;

            usbManager = (UsbManager) this.context.getSystemService(Context.USB_SERVICE);

            final UsbAccessory[] accessoryList = usbManager.getAccessoryList();

            if (accessoryList == null || accessoryList.length == 0) {
                onError("no accessory found");
                MyLog.w(TAG, "no accessory found");
            } else {
                MyLog.w(TAG, "accessoryList = " + accessoryList.length);
                openAccessory(accessoryList[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send(byte[] payload) {
        if (sendHandler != null) {
            Message msg = sendHandler.obtainMessage();
            msg.obj = payload;
            sendHandler.sendMessage(msg);
        }
    }

    private void receive(final byte[] payload, final int length) {
        onReceive(payload, length);
    }

    public abstract void onReceive(final byte[] payload, final int length);

    public abstract void onError(String msg);

    public abstract void onConnected();

    public abstract void onDisconnected();


    private class CommunicationThread extends Thread {
        @Override
        public void run() {
            running = true;

            while (running) {
                byte[] msg = new byte[Constants.BUFFER_SIZE_IN_BYTES];
                try {
                    //Handle incoming messages
                    int len = inStream.read(msg);
                    while (inStream != null && len > 0 && running) {
                        receive(msg, len);
                        Thread.sleep(10);
                        len = inStream.read(msg);
                    }
                } catch (final Exception e) {
                    onError("USB Receive Failed " + e.toString() + "\n");
                    MyLog.w(TAG, "USB Receive Failed " + e.toString());
                    e.printStackTrace();
                    closeAccessory();
                }
            }
        }
    }

    private void openAccessory(UsbAccessory accessory) {
        MyLog.i(TAG, "openAccessory");
        fileDescriptor = usbManager.openAccessory(accessory);
        if (fileDescriptor != null) {

            FileDescriptor fd = fileDescriptor.getFileDescriptor();
            inStream = new FileInputStream(fd);
            outStream = new FileOutputStream(fd);

            new CommunicationThread().start();

            sendHandler = new Handler() {
                public void handleMessage(Message msg) {
                    try {
                        outStream.write((byte[]) msg.obj);
                    } catch (final Exception e) {
                        onError("USB Send Failed " + e.toString() + "\n");
                    }
                }
            };

            onConnected();
        } else {
            onError("could not connect");
        }
    }

    public void closeAccessory() {
        MyLog.i(TAG, "closeAccessory");
        running = false;

        try {
            if (fileDescriptor != null) {
                fileDescriptor.close();
            }
        } catch (IOException e) {
        } finally {
            fileDescriptor = null;
        }

        onDisconnected();
    }

}