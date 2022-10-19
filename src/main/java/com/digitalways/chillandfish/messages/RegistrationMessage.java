/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digitalways.chillandfish.messages;

import com.digitalways.chillandfish.persistence.Address;
import com.digitalways.chillandfish.persistence.ContactData;
import com.digitalways.chillandfish.persistence.FinancialData;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author huba
 */
public class RegistrationMessage extends BaseMessage {

    @JsonProperty("loginname")
    private String loginname;

    @JsonProperty("password")
    private String Password;

    @JsonProperty("groupName")
    private String groupName;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("displayName")
    private String displayName;

    @JsonProperty("logMeIn")
    private Boolean logMeIn;

    @JsonProperty("createUserID")
    private Long createUserId;

    @JsonProperty("address") //TODO solve serialization issues
    @JsonSerialize(as = Address.class)
    private Address address;

    @JsonProperty("financialInfo") //TODO solve serialization issues
    @JsonSerialize(as = FinancialData.class)
    private FinancialData financialInfo;

    @JsonProperty("contactData") //TODO solve serialization issues
    @JsonSerialize(as = ContactData.class)
    private ContactData contactData;


    public String getLoginname() {
        return loginname;
    }

    public String getPassword() {
        return Password;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Boolean getLogMeIn() {
        return logMeIn;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDisplayName() {
        return (displayName != null) ? this.displayName : firstName + " " + lastName;
    }

    public Address getAddress() {
        return address;
    }

    public FinancialData getFinancialInfo() {
        return financialInfo;
    }

    public ContactData getContactData() {
        return contactData;
    }

    public Long getCreateUserId() {
        return createUserId;
    }
}
