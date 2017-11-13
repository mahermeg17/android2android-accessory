package de.quandoo.android2androidaccessory.protocol.command;


import com.akka.aat.bs.enums.TestStatus;
import com.akka.aat.bs.manager.CommandExecutionCallback;
import com.akka.aat.bs.protocol.model.AATCommandArgs;
import com.akka.aat.bs.protocol.model.CmdMessage;

/**
 * @author Maher.MEGADMINI
 * @version 1.0
 * @created 07-ao�t-2017 11:29:50
 */
public abstract class AATTest extends AATCommand {

    private String name;
    private String description;
    private TestStatus testStatus;
    private String comment;

    public abstract CmdMessage prepare(AATCommandArgs aatCommandArgs);

    @Override
    public void execute(CommandExecutionCallback cmdCallback, CmdMessage msg) {

    }

    @Override
    public float updateProgress() {
        return 0;
    }

    @Override
    public int updateRemainingTime() {
        return 0;
    }

    public abstract void interpretTestResult();

}