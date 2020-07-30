package ru.kgeu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.kgeu.model.entity.Role;
import ru.kgeu.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<User> findByRole(Role role);

    User findByRoleAndUsername(Role role, String username);
}
