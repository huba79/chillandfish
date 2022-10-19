package com.digitalways.chillandfish.messages;

import com.digitalways.chillandfish.persistence.Address;
import com.digitalways.chillandfish.persistence.ContactData;
import com.digitalways.chillandfish.persistence.FinancialData;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * @author huba
 */
public class RegistrationMessage implements Serializable {

    @JsonProperty("loginname")
    private String loginname;

    @JsonProperty("password")
    private String password;

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

    @JsonProperty("address")
    @JsonSerialize(as = Address.class)
    private Address address;

    @JsonProperty("financialInfo")
    @JsonSerialize(as = FinancialData.class)
    private FinancialData financialInfo;

    @JsonProperty("contactData")
    @JsonSerialize(as = ContactData.class)
    private ContactData contactData;


    public String getLoginname() {
        return loginname;
    }

    public String getPassword() {
        return password;
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
