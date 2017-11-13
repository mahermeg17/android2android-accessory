package de.quandoo.android2androidaccessory.protocol.model;

import com.akka.aat.bs.protocol.model.installation.BackupConnectionReport;
import com.akka.aat.bs.protocol.model.installation.Inventory;
import com.akka.aat.bs.protocol.model.installation.PrimaryConnectionReport;
import com.akka.aat.bs.protocol.model.installation.RadioReport;
import com.akka.aat.bs.protocol.model.installation.ServiceReport;
import com.akka.aat.bs.protocol.model.installation.Site;
import com.akka.aat.bs.protocol.model.installation.Tap;
import com.akka.aat.bs.protocol.model.installation.UserIdentity;

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