package com.digitalways.chillandfish.messages;

import com.digitalways.chillandfish.persistence.Address;
import com.digitalways.chillandfish.persistence.ContactData;
import com.digitalways.chillandfish.persistence.FinancialData;
import com.digitalways.chillandfish.persistence.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

public class UserResponse {
    @JsonProperty("userId")
    private Long id;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("displayName")
    private String displayName;

    @JsonProperty("loginName")
    private String loginName;
    @JsonProperty("active")
    private Boolean active;
    @JsonProperty("address")
    @JsonSerialize(as = Address.class)
    private Address address;

    @JsonProperty("contactData")
    @JsonSerialize(as = ContactData.class)
    private ContactData contactData;

    @JsonProperty("roles")
    private List<Role> roles;

    @JsonProperty("financialData")
    @JsonSerialize(as = FinancialData.class)
    private FinancialData financialData;

    public UserResponse(Long id, String firstName, String lastName, String displayName, String loginName, Boolean active,
                        Address address, ContactData contactData, List<Role> roles, FinancialData financialData) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.displayName = displayName;
        this.loginName = loginName;
        this.active = active;
        this.address = address;
        this.contactData = contactData;
        this.roles = roles;
        this.financialData = financialData;
    }
}
