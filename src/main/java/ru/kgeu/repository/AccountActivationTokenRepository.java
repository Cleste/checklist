package ru.kgeu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.kgeu.model.entity.AccountActivationToken;

@Repository
public interface AccountActivationTokenRepository extends JpaRepository<AccountActivationToken, Long> {
    AccountActivationToken findByToken(String token);
}

