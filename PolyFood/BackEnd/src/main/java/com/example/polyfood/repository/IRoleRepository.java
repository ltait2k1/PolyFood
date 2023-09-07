package com.example.polyfood.repository;

import com.example.polyfood.models.Role;
import com.example.polyfood.models.Roles.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByRoleName(ERole name);
}
