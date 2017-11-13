package de.quandoo.android2androidaccessory.protocol.model;

/**
 * @author Maher.MEGADMINI
 * @version 1.0
 * @created 07-ao�t-2017 11:26:36
 */
public class OverrideConf extends AATCommandArgs {

    private String endOfChunkChar;
    private int tapAATChunkSize;

    public OverrideConf() {

    }

    public String getEndOfChunkChar() {
        return endOfChunkChar;
    }

    public void setEndOfChunkChar(String endOfChunkChar) {
        this.endOfChunkChar = endOfChunkChar;
    }

    public int getTapAATChunkSize() {
        return tapAATChunkSize;
    }

    public void setTapAATChunkSize(int tapAATChunkSize) {
        this.tapAATChunkSize = tapAATChunkSize;
    }
}