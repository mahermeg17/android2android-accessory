package de.quandoo.android2androidaccessory.protocol.command;


import com.akka.aat.bs.enums.CommandStatus;
import com.akka.aat.bs.manager.CommandExecutionCallback;
import com.akka.aat.bs.protocol.Command;
import com.akka.aat.bs.protocol.model.AATCommandArgs;
import com.akka.aat.bs.protocol.model.CmdMessage;

import java.util.Date;
import java.util.List;

public abstract class AATCommand {

    protected Command cmd;
    protected int timeout;
    protected CommandStatus cmdStatus;
    protected CmdMessage requestMessage;
    protected CmdMessage answerMessage;
    protected List<String> multipartAnswer;
    protected List<String> multilineLog;
    protected Date lastRun;

    protected AATCommand() {
    }

    protected AATCommand(Command cmd) {
    }

    public abstract CmdMessage prepare(AATCommandArgs aatCommandArgs);

    /**
     * @param cmdCallback
     * @param msg
     */
    public abstract void execute(CommandExecutionCallback cmdCallback, CmdMessage msg);

    /**
     * @param chunkMsg
     */
    public abstract boolean updateAnswer(String chunkMsg);

    /**
     * @param logLine
     */
    public abstract void updateLogLigne(String logLine);

    public abstract float updateProgress();

    public abstract int updateRemainingTime();

    public Command getCmd() {
        return cmd;
    }

    public void setCmd(Command cmd) {
        this.cmd = cmd;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public CommandStatus getCmdStatus() {
        return cmdStatus;
    }

    public void setCmdStatus(CommandStatus cmdStatus) {
        this.cmdStatus = cmdStatus;
    }

    public CmdMessage getRequestMessage() {
        return requestMessage;
    }

    public void setRequestMessage(CmdMessage requestMessage) {
        this.requestMessage = requestMessage;
    }

    public CmdMessage getAnswerMessage() {
        return answerMessage;
    }

    public void setAnswerMessage(CmdMessage answerMessage) {
        this.answerMessage = answerMessage;
    }

    public List<String> getMultipartAnswer() {
        return multipartAnswer;
    }

    public void setMultipartAnswer(List<String> multipartAnswer) {
        this.multipartAnswer = multipartAnswer;
    }

    public List<String> getMultilineLog() {
        return multilineLog;
    }

    public void setMultilineLog(List<String> multilineLog) {
        this.multilineLog = multilineLog;
    }

    public Date getLastRun() {
        return lastRun;
    }

    public void setLastRun(Date lastRun) {
        this.lastRun = lastRun;
    }
}