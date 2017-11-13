package de.quandoo.android2androidaccessory.protocol.model.installation;

import com.akka.aat.bs.enums.StationInstallationType;

/**
 * @version 1.0
 * @created 07-ao�t-2017 11:27:41
 */
public class Site {

    private boolean aerialPlatform;
    private String lease;
    private String siteName;
    private String siteNumber;
    private String siteReference;
    private StationInstallationType installType;

    public Site() {

    }

    public double calculateCableLoss() {
        return 0;
    }

}