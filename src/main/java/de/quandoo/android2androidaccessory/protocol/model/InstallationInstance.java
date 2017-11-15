package de.quandoo.android2androidaccessory.protocol.model;


import de.quandoo.android2androidaccessory.protocol.model.installation.BackupConnectionReport;
import de.quandoo.android2androidaccessory.protocol.model.installation.Inventory;
import de.quandoo.android2androidaccessory.protocol.model.installation.PrimaryConnectionReport;
import de.quandoo.android2androidaccessory.protocol.model.installation.RadioReport;
import de.quandoo.android2androidaccessory.protocol.model.installation.ServiceReport;
import de.quandoo.android2androidaccessory.protocol.model.installation.Site;
import de.quandoo.android2androidaccessory.protocol.model.installation.Tap;
import de.quandoo.android2androidaccessory.protocol.model.installation.UserIdentity;

/**
 * @author Maher.MEGADMINI
 * @version 1.0
 * @created 07-ao�t-2017 11:27:41
 */
public class InstallationInstance extends AATCommandArgs {

    private BackupConnectionReport backupConnection;
    private UserIdentity identification;
    private Inventory inventory;
    private PrimaryConnectionReport primaryConnection;
    private RadioReport radio;
    private ServiceReport service;
    private Site site;
    private Tap tap;

    public InstallationInstance() {

    }


}