/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digitalways.chillandfish.persistence;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author huba
 */
@Entity
@Table(name="CONTACT_DATA")
public class ContactData extends BaseEntity implements Serializable {
    
    @Column(name="PHONE_NR", columnDefinition="VARCHAR(24)")
    private String phoneNr;
    
    @Column(name="CONTACT_PERSON", columnDefinition="VARCHAR(64)")    
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
