/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digitalways.chillandfish.authentication.persistence;

import com.digitalways.chillandfish.persistence.Address;
import com.digitalways.chillandfish.persistence.BaseEntity;
import com.digitalways.chillandfish.persistence.ContactData;
import com.digitalways.chillandfish.persistence.FinancialData;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author huba
 */
@Entity
@Table (name = "USERS")
public class User extends BaseEntity implements Serializable {

//    Logger logger = LoggerFactory.getLogger(getClass());

    @Column(name="FIRST_NAME",nullable=false)
    private String firstName;
    
    @Column(name="LAST_NAME",nullable=false)
    private String lastName;
    
    @Column(name="LOGIN_NAME",nullable=false)        
    private String loginName;
    
    @Column(name="PASSWORD",nullable=false)     
    private String password;
    
    @Column(name="DISPLAY_NAME",nullable=false) 
    private String displayName;

    @Column(name="ACTIVE",nullable=false)
    private Boolean active = true;
    
    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinTable(
        name = "USER_ROLES", 
        joinColumns = { @JoinColumn(name = "USER_ID") }, 
        inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") }
    )
    //listak sok delete-t generalnak, jobb valamilyen set-et hasznalni
    private List<Role> roles;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")   
    private Address address;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id", referencedColumnName = "id")     
    private ContactData contactInfo;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "financial_id", referencedColumnName = "id")  
    private FinancialData financialData;

    public User() {
    }
    
    public User( String loginName, String password, List<Role> roles,String firstName, String lastName,String nickName, Long createUserId) {
        super(createUserId);
        this.firstName = firstName;
        this.lastName = lastName;
        this.displayName = nickName;
        this.loginName = loginName;
        this.password = password;
        this.roles = roles;
        this.displayName = nickName;
    }

    public User(String firstName, String lastName, String loginName, String password, List<Role> roles, String nickName, Address address, ContactData contactInfo, FinancialData financialData, Long createUserId) {
        super(createUserId);
        this.firstName = firstName;
        this.lastName = lastName;
        this.loginName = loginName;
        this.password = password;
        this.roles = roles;
        this.displayName = nickName;
        this.address = address;
        this.contactInfo = contactInfo;
        this.financialData = financialData;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ContactData getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactData contactInfo) {
        this.contactInfo = contactInfo;
    }

    public FinancialData getFinancialData() {
        return financialData;
    }

    public void setFinancialData(FinancialData financialData) {
        this.financialData = financialData;
    }

    public List<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
