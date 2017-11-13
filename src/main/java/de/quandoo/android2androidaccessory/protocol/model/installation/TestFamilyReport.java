package de.quandoo.android2androidaccessory.protocol.model.installation;

import com.akka.aat.bs.enums.TestStatus;

import java.io.Serializable;
import java.util.List;

/**
 * @author Maher.MEGADMINI
 * @version 1.0
 * @created 07-ao�t-2017 11:27:41
 */
public class TestFamilyReport implements Serializable {

    private List<String> comments;
    private TestStatus status;

    public TestFamilyReport() {

    }

}