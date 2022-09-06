/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digitalways.chillandfish.authentication.messages;

import com.digitalways.chillandfish.authentication.persistence.Role;
import com.digitalways.chillandfish.persistence.ContactData;
import com.digitalways.chillandfish.persistence.Address;
import com.digitalways.chillandfish.persistence.FinancialData;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Set;

/**
 *
 * @author huba
 */
public class RegistrationResponse  extends BaseMessage{

    @JsonProperty("userId")
    private Long userId;
    @JsonProperty("displayname")
    private String displayname;

    @JsonProperty("roles")
    private Set<Role> roles;

    @JsonProperty("address") //TODO solve serialization issues
    @JsonSerialize(as = Address.class)
    private Address address;
    
    @JsonProperty("financialInfo") //TODO solve serialization issues
    @JsonSerialize(as = FinancialData.class)
    private FinancialData financialInfo;
    
    @JsonProperty("contactData") //TODO solve serialization issues
    @JsonSerialize(as = ContactData.class)
    private ContactData contactData;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setFinancialInfo(FinancialData financialInfo) {
        this.financialInfo = financialInfo;
    }

    public void setContactData(ContactData contactData) {
        this.contactData = contactData;
    }

    public RegistrationResponse(Long userId,String displayName, Set<Role> roles,
                                 Address address, FinancialData financialInfo, ContactData contactData) {
        this.userId = userId;
        this.roles = roles;
        this.displayname=displayName;
        this.address = address;
        this.financialInfo = financialInfo;
        this.contactData = contactData;
    }
}
