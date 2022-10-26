package com.digitalways.chillandfish.persistence;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * @author huba
 */
@SuppressWarnings("UnusedAssignment")
@Entity
@Table(name = "USERS")
public class User extends BaseEntity implements Serializable, UserDetails {

    //    Logger logger = LoggerFactory.getLogger(getClass());

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;


    @Column(name = "LOGIN_NAME", nullable = false, unique = true)
    private String loginName;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "DISPLAY_NAME", nullable = false)
    private String displayName;

    @Column(name = "ACTIVE", nullable = false)
    private Boolean active = true;

    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    private ContactData contactInfo;

    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @JoinColumn(name = "financial_id", referencedColumnName = "id")
    private FinancialData financialData;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(
            name = "USERS_ROLES",
            joinColumns = @JoinColumn(name = "user_id")
            , inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new LinkedHashSet<>();
    @Column(name = "EXPIRED", nullable = false)
    private boolean expired;

    @Column(name = "LOCKED", nullable = false)
    private boolean locked;

    @Column(name = "CREDENTIALS_EXPIRED", nullable = false)
    private boolean credentialsExpired;

    @Column(name = "DISABLED", nullable = false)
    private boolean disabled;

    public User() {
    }

    public User(String loginName, String password, Set<Role> roles,
                String firstName, String lastName, String nickName, Long createUserId) {
        super(createUserId);
        this.firstName = firstName;
        this.lastName = lastName;
        this.displayName = nickName;
        this.loginName = loginName;
        this.password = password;
        this.roles = roles;
        this.displayName = nickName;
        this.expired = false;
        this.locked = false;
        this.credentialsExpired = false;
        this.disabled = false;
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
        this.expired = false;
        this.locked = false;
        this.credentialsExpired = false;
        this.disabled = false;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        return authorities;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return loginName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return !disabled;
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

    public Role getRoleByRoleName(String pRoleName) {
        for (Role role : this.getRoles()) {
            if (role.getRoleName().equals(pRoleName)) {
                return role;
            }
        }
        return null;
    }

}
