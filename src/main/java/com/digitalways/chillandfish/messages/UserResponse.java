package com.digitalways.chillandfish.messages;

import com.digitalways.chillandfish.persistence.Address;
import com.digitalways.chillandfish.persistence.ContactData;
import com.digitalways.chillandfish.persistence.FinancialData;
import com.digitalways.chillandfish.persistence.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
public class UserResponse {
    @SuppressWarnings("FieldCanBeLocal")
    @JsonProperty("userId")
    private Long id;
    @SuppressWarnings("FieldCanBeLocal")
    @JsonProperty("firstName")
    private String firstName;
    @SuppressWarnings("FieldCanBeLocal")
    @JsonProperty("lastName")
    private String lastName;
    @SuppressWarnings("FieldCanBeLocal")
    @JsonProperty("displayName")
    private String displayName;

    @SuppressWarnings("FieldCanBeLocal")
    @JsonProperty("loginName")
    private String loginName;
    @SuppressWarnings("FieldCanBeLocal")
    @JsonProperty("active")
    private Boolean active;
    @SuppressWarnings("FieldCanBeLocal")
    @JsonProperty("address")
    @JsonSerialize(as = Address.class)
    private Address address;

    @SuppressWarnings("FieldCanBeLocal")
    @JsonProperty("contactData")
    @JsonSerialize(as = ContactData.class)
    private ContactData contactData;

    @SuppressWarnings("FieldCanBeLocal")
    @JsonProperty("roles")
    private List<Role> roles;

    @SuppressWarnings("FieldCanBeLocal")
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
