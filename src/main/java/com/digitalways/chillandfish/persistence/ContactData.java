package com.digitalways.chillandfish.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author huba
 */
@Entity
@Table(name = "CONTACT_DATA")
public class ContactData extends BaseEntity implements Serializable {

    @Column(name = "PHONE_NR")
    private String phoneNr;

    @Column(name = "CONTACT_PERSON")
    private String contactPerson;

    public ContactData() {
    }

    public ContactData(String phoneNr, String contactPerson, Long createUserId) {
        super(createUserId);
        this.phoneNr = phoneNr;
        this.contactPerson = contactPerson;

    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

}
