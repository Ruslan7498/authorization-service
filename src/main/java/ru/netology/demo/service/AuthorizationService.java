package ru.netology.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.netology.demo.exception.InvalidCredentials;
import ru.netology.demo.exception.UnauthorizedUser;
import ru.netology.demo.model.Authorities;
import ru.netology.demo.repository.UserRepository;

import java.util.List;

@Service
public class AuthorizationService {
    @Autowired
    private UserRepository userRepository;

    @ExceptionHandler({InvalidCredentials.class, UnauthorizedUser.class})
    public List<Authorities> getAuthorities(String user, String password) {
        if (isEmpty(user) || isEmpty(password)) {
            throw new InvalidCredentials("User name or password is empty");
        }

        List<Authorities> userAuthorities = userRepository.getUserAuthorities(user, password);

        if (isEmpty(userAuthorities)) {
            throw new UnauthorizedUser("Unknown user " + user);
        }
        return userAuthorities;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmpty(List<?> str) {
        return str == null || str.isEmpty();
    }
}