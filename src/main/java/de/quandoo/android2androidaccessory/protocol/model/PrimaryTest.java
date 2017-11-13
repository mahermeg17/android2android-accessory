package de.quandoo.android2androidaccessory.protocol.model;

import com.akka.aat.bs.enums.ConnectionType;

/**
 * @author Maher.MEGADMINI
 * @version 1.0
 * @created 07-ao�t-2017 11:26:37
 */
public class PrimaryTest extends AATCommandArgs {

    private boolean auto;
    private boolean forced;
    private String login;
    private String password;
    private String primConnSfxModel;
    private ConnectionType primConnType;
    private int timeout;

    public PrimaryTest() {

    }


}