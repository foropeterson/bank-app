package com.example.banking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Roles {
    public interface RolesRepository extends JpaRepository<Roles,Long> {

    }
}
