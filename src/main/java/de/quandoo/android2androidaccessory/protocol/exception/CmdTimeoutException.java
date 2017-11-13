package de.quandoo.android2androidaccessory.protocol.exception;

/**
 * Created by Maher.MEGADMINI on 08/08/2017.
 */

public class CmdTimeoutException extends RuntimeException {
    public CmdTimeoutException() {
        super();
    }

    public CmdTimeoutException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public CmdTimeoutException(String detailMessage) {
        super(detailMessage);
    }

    public CmdTimeoutException(Throwable throwable) {
        super(throwable);
    }
}
