package com.adnanarch.loginapp.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Author: Adnan Rafique
 * Date: 4/9/2024
 * Time: 2:14 AM
 * Version: 1.0
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
    Optional<Role> findByName(String name);
}
