package ru.kgeu.service.api;

import javax.servlet.http.HttpServletRequest;

import ru.kgeu.model.entity.AccountActivationToken;
import ru.kgeu.model.entity.User;

public interface AccountActivationTokenService {
    AccountActivationToken generateToken(User user);

    AccountActivationToken findByToken(String token);

    void delete(AccountActivationToken token);
}
