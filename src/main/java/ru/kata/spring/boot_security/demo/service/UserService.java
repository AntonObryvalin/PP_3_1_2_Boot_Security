package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {
    // Сохранение нового пользователя
    void saveUser(User user);

    // Обновление информации о пользователе
    void updateUser(User user);

    // Удаление пользователя по ID
    void deleteUser(Long id);

    // Получение пользователя по ID
    User getUserById(Long id);

    // Получение всех пользователей
    List<User> getAllUsers();

    // Получение пользователя по email (для аутентификации)
    User getUserByName(String email);


}
