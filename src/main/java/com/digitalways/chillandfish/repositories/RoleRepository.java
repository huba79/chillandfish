/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.digitalways.chillandfish.repositories;

import com.digitalways.chillandfish.persistence.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author huba
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleById(Long id);

    Optional<Role> findRoleByRoleName(String roleName);
}
