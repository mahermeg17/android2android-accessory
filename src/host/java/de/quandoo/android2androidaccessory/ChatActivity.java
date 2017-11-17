package de.quandoo.android2androidaccessory;

import android.app.Activity;
import android.content.Context;
import android.hardware.usb.UsbConstants;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import butterknife.BindView;
import de.quandoo.android2androidaccessory.protocol.command.HeartbeatCmd;
import de.quandoo.android2androidaccessory.protocol.model.CmdMessage;

public class ChatActivity extends BaseChatActivity {

    public static final String TAG = "ChatActivity";

    private final AtomicBoolean keepThreadAlive = new AtomicBoolean(true);
    private final List<String> sendBuffer = new ArrayList<>();
    Activity chatActivity;

    @BindView(R.id.over_conf_cmd)
    Button overConfCMD;
    @BindView(R.id.get_version_cmd)
    Button getVersionCMD;
    @BindView(R.id.get_tapid_cmd)
    Button getTapIdCMD;
    @BindView(R.id.get_tap_info_cmd)
    Button getTapInfoCMD;
    @BindView(R.id.get_saved_inst_t_cmd)
    Button getSavedInstTimeCMD;
    @BindView(R.id.get_saved_inst_cmd)
    Button getSavedInstCMD;
    @BindView(R.id.send_inst_cmd)
    Button sendInstCMD;
    @BindView(R.id.lna_sc_test_cmd)
    Button lnaScTestCMD;

    @BindView(R.id.line_0)
    View line_0;
    @BindView(R.id.line_1)
    View line_1;

    private Handler mHandler = new Handler();

    final Runnable mRunnable = new Runnable() {
        public void run() {
            chatActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    MyLog.i(TAG, "USB_MODE = " + USBApp.USB_MODE);
                    if (USBApp.USB_MODE == USBApp.USB_ON) {
                        HeartbeatCmd heartbeatCmd = new HeartbeatCmd();
                        CmdMessage cmdMessage = heartbeatCmd.prepare(null);
                        String msg = ATTUtils.convertObjectToJson(cmdMessage, false);
                        sendString(msg);
                    }
                }
            });
            mHandler.postDelayed(mRunnable, USBApp.HEARTBEAT_FREQUENCY);
        }
    };

    @Override
    protected void sendString(final String string) {
        MyLog.i(TAG, "try to send : " + getString(R.string.local_prompt) + string);
        sendBuffer.add(string);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        line_0.setVisibility(View.VISIBLE);
        line_1.setVisibility(View.VISIBLE);

        chatActivity = this;
        mHandler.postDelayed(mRunnable, 5000);

        new Thread(new CommunicationRunnable()).start();
    }

    private class CommunicationRunnable implements Runnable {

        @Override
        public void run() {
            final UsbManager usbManager = (UsbManager) getSystemService(Context.USB_SERVICE);

            final UsbDevice device = getIntent().getParcelableExtra(ConnectActivity.DEVICE_EXTRA_KEY);

            UsbEndpoint endpointIn = null;
            UsbEndpoint endpointOut = null;

            final UsbInterface usbInterface = device.getInterface(0);

            for (int i = 0; i < device.getInterface(0).getEndpointCount(); i++) {

                final UsbEndpoint endpoint = device.getInterface(0).getEndpoint(i);
                if (endpoint.getDirection() == UsbConstants.USB_DIR_IN) {
                    endpointIn = endpoint;
                }
                if (endpoint.getDirection() == UsbConstants.USB_DIR_OUT) {
                    endpointOut = endpoint;
                }

            }

            if (endpointIn == null) {
                printLineToUI("Input Endpoint not found");
                return;
            }

            if (endpointOut == null) {
                printLineToUI("Output Endpoint not found");
                return;
            }

            final UsbDeviceConnection connection = usbManager.openDevice(device);

            if (connection == null) {
                printLineToUI("Could not open device");
                return;
            }

            final boolean claimResult = connection.claimInterface(usbInterface, true);

            if (!claimResult) {
                printLineToUI("Could not claim device");
            } else {
                final byte buff[] = new byte[Constants.BUFFER_SIZE_IN_BYTES];
                printLineToUI("Claimed interface - ready to communicate");

                while (keepThreadAlive.get()) {
                    USBApp.USB_MODE = USBApp.USB_ON;
                    final int bytesTransferred = connection.bulkTransfer(endpointIn, buff, buff.length, Constants.USB_TIMEOUT_IN_MS);
                    if (bytesTransferred > 0) {
                        printLineToUI("device> " + new String(buff, 0, bytesTransferred));
                    }

                    synchronized (sendBuffer) {
                        if (sendBuffer.size() > 0) {
                            final byte[] sendBuff = sendBuffer.get(0).toString().getBytes();
                            connection.bulkTransfer(endpointOut, sendBuff, sendBuff.length, Constants.USB_TIMEOUT_IN_MS);
                            sendBuffer.remove(0);
                        }
                    }
                }
            }
            USBApp.USB_MODE = USBApp.USB_OFF;
            connection.releaseInterface(usbInterface);
            connection.close();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        USBApp.USB_MODE = USBApp.USB_OFF;
        keepThreadAlive.set(false);
    }
}
