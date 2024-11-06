package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository; // Импортируйте ваш репозиторий
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.security.CustomUserDetails;

import java.util.Optional;

// Сервис для получения пользователя по имени
@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    private final UserRepository userRepository;


    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Загружает пользователя по имени
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Используем Optional для безопасного извлечения пользователя
        Optional<User> user = Optional.ofNullable(userRepository.findByName(username));
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        // Возвращаем CustomUserDetails
        return new CustomUserDetails(user.get());
    }
}
