package de.quandoo.android2androidaccessory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.quandoo.android2androidaccessory.manager.CommandExecutionCallback;
import de.quandoo.android2androidaccessory.protocol.command.HeartbeatCmd;
import de.quandoo.android2androidaccessory.protocol.model.CmdMessage;

public abstract class BaseChatActivity extends AppCompatActivity {

    public static final String TAG = "BaseChatActivity";

    @BindView(R.id.content_text)
    TextView contentTextView;

    @BindView(R.id.input_edittext)
    EditText input;

    CommandExecutionCallback heartbeatCmdCallback = new CommandExecutionCallback() {
        @Override
        public void onError() {

        }

        @Override
        public void onReadingCompleted() {

        }

        @Override
        public void onReadingMessage() {

        }

        @Override
        public void onWriteCompleted() {

        }

        @Override
        public void onWritingChunk() {

        }
    };

    @OnClick(R.id.send_button)
    public void onButtonClick() {
        final String inputString = input.getText().toString();
        if (inputString.length() == 0) {
            return;
        }

        sendString(inputString);
        printLineToUI(getString(R.string.local_prompt) + inputString);
        input.setText("");
    }

    @OnClick(R.id.heartbeat_cmd)
    public void heartbeatCmd() {
        HeartbeatCmd heartbeatCmd = new HeartbeatCmd();
        CmdMessage cmdMessage = heartbeatCmd.prepare(null);
        String msg = ATTUtils.convertObjectToJson(cmdMessage);
        sendString(msg);
        printLineToUI(getString(R.string.local_prompt) + msg);

    }

    protected abstract void sendString(final String string);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

    }

    protected void printLineToUI(final String line) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MyLog.i(TAG, line);
                contentTextView.setText(contentTextView.getText() + "\n" + line);
            }
        });
    }

}
