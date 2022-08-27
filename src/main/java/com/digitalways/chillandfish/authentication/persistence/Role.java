/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digitalways.chillandfish.authentication.persistence;

import com.digitalways.chillandfish.persistence.BaseEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.annotations.ColumnDefault;

/**
 *
 * @author huba
 */
@Entity
@Table(name="ROLES")
public class Role extends BaseEntity implements Serializable{
    
    @Column(name="ROLE_NAME",nullable=false)
    private String roleName;
    
    @Column(name="LANG",nullable=false)   
    @ColumnDefault("EN")
    private String language;
    
    @ManyToMany(mappedBy = "roles")
    //TODO: listak sok delete-t generalnak, jobb valamilyen set-et hasznalni
    private List<User> users;
    
    public Role() {
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    
    
    
    
}
