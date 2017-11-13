package de.quandoo.android2androidaccessory.protocol.exception;

/**
 * Created by Maher.MEGADMINI on 08/08/2017.
 */

public class CmdMessageFormatingException extends RuntimeException {
    public CmdMessageFormatingException() {
        super();
    }

    public CmdMessageFormatingException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public CmdMessageFormatingException(String detailMessage) {
        super(detailMessage);
    }

    public CmdMessageFormatingException(Throwable throwable) {
        super(throwable);
    }
}
