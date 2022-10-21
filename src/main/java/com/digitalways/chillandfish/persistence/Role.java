package com.digitalways.chillandfish.persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author huba
 */
@Entity
@Table(name = "ROLES")
public class Role extends BaseEntity implements Serializable {

    @Column(name = "ROLE_NAME", nullable = false)
    private String roleName;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    private Set<User> users;

    public Role() {
    }

    public Role(Long createUserId, String roleName) {
        super(createUserId);
        this.roleName = roleName;
    }

    public Role(Long createUserId) {
        super(createUserId);
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
