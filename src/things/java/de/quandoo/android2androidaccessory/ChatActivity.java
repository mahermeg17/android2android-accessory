package de.quandoo.android2androidaccessory;

import android.os.Bundle;
import android.util.Log;

public class ChatActivity extends BaseChatActivity {

    private AccessoryCommunicator communicator;
    private static final String TAG = "ChatActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");

        communicator = new AccessoryCommunicator(this) {

            @Override
            public void onReceive(byte[] payload, int length) {
                Log.i(TAG, "onReceive");
                Log.i(TAG, "payload=" + payload);
                printLineToUI("host> " + new String(payload, 0, length));
            }

            @Override
            public void onError(String msg) {
                Log.i(TAG, "onError");
                Log.i(TAG, "msg=" + msg);
                printLineToUI("notify" + msg);
            }

            @Override
            public void onConnected() {
                Log.i(TAG, "onConnected");
                printLineToUI("connected");
            }

            @Override
            public void onDisconnected() {
                Log.i(TAG, "onDisconnected");
                printLineToUI("disconnected");
            }
        };
    }


    @Override
    protected void sendString(String string) {
        Log.i(TAG, "communicator.send=" + string);
        communicator.send(string.getBytes());
    }
}
