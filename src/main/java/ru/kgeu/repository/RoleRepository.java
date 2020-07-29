package ru.kgeu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kgeu.model.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}