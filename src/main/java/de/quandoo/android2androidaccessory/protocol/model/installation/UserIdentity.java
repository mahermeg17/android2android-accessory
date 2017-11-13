package de.quandoo.android2androidaccessory.protocol.model.installation;

import java.io.Serializable;
import java.util.Date;

/**
 * @version 1.0
 * @created 07-ao�t-2017 11:27:41
 */
public class UserIdentity implements Serializable {

    private String company;
    private String firstName;
    private String name;
    private Date lastConnection;
    private String uid;

    public UserIdentity() {

    }

    public UserIdentity(String firstName, String name, String company) {
        this.company = company;
        this.firstName = firstName;
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastConnection() {
        return lastConnection;
    }

    public void setLastConnection(Date lastConnection) {
        this.lastConnection = lastConnection;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "UserIdentity{" +
                "company='" + company + '\'' +
                ", firstName='" + firstName + '\'' +
                ", name='" + name + '\'' +
                ", lastConnection=" + lastConnection +
                '}';
    }
}