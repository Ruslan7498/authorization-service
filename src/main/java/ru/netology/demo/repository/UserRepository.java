package ru.netology.demo.repository;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Repository;
import ru.netology.demo.model.Authorities;

import java.util.Arrays;
import java.util.List;

@Repository
@ConfigurationProperties("authorization")
@Data
public class UserRepository {
    private String nameUser;
    private String passwordUser;

    public List<Authorities> getUserAuthorities(String user, String password) {
        if (user.equals(nameUser) & password.equals(passwordUser)) {
            return Arrays.asList(Authorities.READ, Authorities.WRITE, Authorities.DELETE);
        } else {
            return null;
        }
    }
}