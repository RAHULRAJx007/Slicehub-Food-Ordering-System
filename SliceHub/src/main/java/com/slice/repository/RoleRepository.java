package com.slice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.slice.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<RoleRepository, Long> {
	Optional<Role> findByName(String nameString);

}
