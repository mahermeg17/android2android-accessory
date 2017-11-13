package de.quandoo.android2androidaccessory.protocol.command;

import com.akka.aat.bs.manager.CommandExecutionCallback;
import com.akka.aat.bs.protocol.Command;
import com.akka.aat.bs.protocol.model.AATCommandArgs;
import com.akka.aat.bs.protocol.model.CmdMessage;

/**
 * Created by Maher.MEGADMINI on 07/08/2017.
 */

public class MSpecsCmd extends AATCommand implements CommandExecutionCallback {

    public MSpecsCmd() {
        super();
        super.cmd = Command.GET_MSPECS;
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
