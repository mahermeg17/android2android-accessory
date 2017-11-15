package de.quandoo.android2androidaccessory.protocol.model;

/**
 * @author Maher.MEGADMINI
 * @version 1.0
 * @created 07-ao�t-2017 11:26:34
 */
public class CmdMessage {

    private String ans;
    private String arg;
    private String cmd;

    public CmdMessage() {

    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public String getArg() {
        return arg;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }
}