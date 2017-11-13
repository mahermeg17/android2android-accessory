package de.quandoo.android2androidaccessory.manager;

/**
 * @author Maher.MEGADMINI
 * @version 1.0
 * @created 07-ao�t-2017 11:24:19
 */
public interface CommandExecutionCallback {


    public void onError();

    public void onReadingCompleted();

    public void onReadingMessage();

    public void onWriteCompleted();

    public void onWritingChunk();

}