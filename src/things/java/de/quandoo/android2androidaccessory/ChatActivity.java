package de.quandoo.android2androidaccessory;

import android.os.Bundle;

public class ChatActivity extends BaseChatActivity {

    private AccessoryCommunicator communicator;
    private static final String TAG = "--ChatActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyLog.i(TAG, "onCreate");

        communicator = new AccessoryCommunicator(this) {

            @Override
            public void onReceive(byte[] payload, int length) {
                MyLog.i(TAG, "onReceive");
                MyLog.i(TAG, "payload=" + payload);
                printLineToUI("host> " + new String(payload, 0, length));
            }

            @Override
            public void onError(String msg) {
                MyLog.i(TAG, "onError");
                MyLog.i(TAG, "msg=" + msg);
                printLineToUI("notify" + msg);
            }

            @Override
            public void onConnected() {
                MyLog.i(TAG, "onConnected");
                printLineToUI("connected");
            }

            @Override
            public void onDisconnected() {
                MyLog.i(TAG, "onDisconnected");
                printLineToUI("disconnected");
            }
        };
    }


    @Override
    protected void sendString(String string) {
        MyLog.i(TAG, "communicator.send=" + string);
        communicator.send(string.getBytes());
    }
}
