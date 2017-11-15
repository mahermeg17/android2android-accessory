package de.quandoo.android2androidaccessory.protocol.command;


import de.quandoo.android2androidaccessory.manager.CommandExecutionCallback;
import de.quandoo.android2androidaccessory.protocol.Command;
import de.quandoo.android2androidaccessory.protocol.model.AATCommandArgs;
import de.quandoo.android2androidaccessory.protocol.model.CmdMessage;

/**
 * Created by Maher.MEGADMINI on 07/08/2017.
 */

public class TapFeederCmd extends AATTest implements CommandExecutionCallback {

    public TapFeederCmd() {
        super();
        super.cmd = Command.TAP_FEEDER_LNA_TEST;
    }


    @Override
    public CmdMessage prepare(AATCommandArgs aatCommandArgs) {
        return null;
    }

    @Override
    public void execute(CommandExecutionCallback cmdCallback, CmdMessage msg) {

    }

    @Override
    public boolean updateAnswer(String chunkMsg) {
        return true;
    }

    @Override
    public void updateLogLigne(String logLine) {

    }

    @Override
    public float updateProgress() {
        return 0;
    }

    @Override
    public int updateRemainingTime() {
        return 0;
    }

    @Override
    public void interpretTestResult() {

    }

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
}
