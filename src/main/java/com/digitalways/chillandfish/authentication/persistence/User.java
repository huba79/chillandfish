package com.digitalways.chillandfish.authentication.persistence;

import com.digitalways.chillandfish.persistence.Address;
import com.digitalways.chillandfish.persistence.BaseEntity;
import com.digitalways.chillandfish.persistence.ContactData;
import com.digitalways.chillandfish.persistence.FinancialData;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

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
    

    @Column(name="LOGIN_NAME",nullable=false,unique = true)
    private String loginName;
    
    @Column(name="PASSWORD",nullable=false)     
    private String password;

    @Column(name="DISPLAY_NAME",nullable=false) 
    private String displayName;

    @Column(name="ACTIVE",nullable=false)
    private Boolean active = true;

    @OneToOne(cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
    @JoinColumn(name = "address_id", referencedColumnName = "id")   
    private Address address;
    
    @OneToOne(cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
    @JoinColumn(name = "contact_id", referencedColumnName = "id")     
    private ContactData contactInfo;
    
    @OneToOne(cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
    @JoinColumn(name = "financial_id", referencedColumnName = "id")  
    private FinancialData financialData;
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    @JoinTable(
            name = "USERS_ROLES",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new LinkedHashSet<>();
    public User() {
    }
    
    public User( String loginName, String password, Set<Role> roles,
                 String firstName, String lastName,String nickName, Long createUserId) {
                    super(createUserId);
                    this.firstName = firstName;
                    this.lastName = lastName;
                    this.displayName = nickName;
                    this.loginName = loginName;
                    this.password = password;
                    this.roles = roles;
                    this.displayName = nickName;
    }

    public User(String firstName, String lastName, String loginName,
                String password, Set<Role> roles, String nickName, Address address, ContactData contactInfo, FinancialData financialData, Long createUserId) {
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> userRoles) {
        this.roles = userRoles;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Role getRoleByRoleName(String pRoleName){
        for(Role role:this.getRoles()){
            if(role.getRoleName().equals(pRoleName)) {
                return role;
            }
        } return null;
    }

}
