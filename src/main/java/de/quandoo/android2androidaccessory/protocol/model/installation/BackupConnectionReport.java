package de.quandoo.android2androidaccessory.protocol.model.installation;


import de.quandoo.android2androidaccessory.enums.ConnectionType;

/**
 * @author Maher.MEGADMINI
 * @version 1.0
 * @created 07-ao�t-2017 11:27:41
 */
public class BackupConnectionReport extends TestFamilyReport {

    private TestReport backupConnectionConfigurationTest;
    private TestReport backupConnectionTest;
    private String presence;
    private ConnectionType type;

    public BackupConnectionReport() {

    }

    public void finalize() throws Throwable {
        super.finalize();
    }

}