package ru.kata.spring.boot_security.demo.security;

import ru.kata.spring.boot_security.demo.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

// Класс, который реализует UserDetails
public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles(); // Вернуть роли пользователя
    }

    @Override
    public String getPassword() {
        return user.getPassword(); // Вернуть пароль
    }

    @Override
    public String getUsername() {
        return user.getName(); // Вернуть имя пользователя
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Учетная запись не истекла
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Учетная запись не заблокирована
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Учетные данные не истекли
    }

    @Override
    public boolean isEnabled() {
        return true; // Учетная запись активна
    }
}
