package ru.kgeu.service.impl;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.kgeu.model.entity.AccountActivationToken;
import ru.kgeu.model.entity.User;
import ru.kgeu.repository.AccountActivationTokenRepository;
import ru.kgeu.service.api.AccountActivationTokenService;

@Log4j2
@Service
@RequiredArgsConstructor
public class AccountActivationTokenServiceImpl implements AccountActivationTokenService {

    private final AccountActivationTokenRepository tokenRepository;

    @Override
    @Transactional
    public AccountActivationToken generateToken(User user) {
        AccountActivationToken token = new AccountActivationToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        tokenRepository.save(token);
        return token;
    }

    @Override
    @Transactional(readOnly = true)
    public AccountActivationToken findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    @Override
    @Transactional
    public void delete(AccountActivationToken token) {
        tokenRepository.delete(token);
    }
}
