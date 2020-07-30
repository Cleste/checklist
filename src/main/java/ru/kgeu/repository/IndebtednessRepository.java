package ru.kgeu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.kgeu.model.entity.Indebtedness;
import ru.kgeu.model.entity.Role;
import ru.kgeu.model.entity.User;

@Repository
public interface IndebtednessRepository extends JpaRepository<Indebtedness, Long> {
    Indebtedness findByRoleAndStudent(Role role, User student);
}
