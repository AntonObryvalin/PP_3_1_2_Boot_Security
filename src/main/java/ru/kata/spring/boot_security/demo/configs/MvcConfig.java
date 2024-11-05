package ru.kata.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Класс конфигурации MVC для настройки контроллеров представлений.
 *
 * Этот класс добавляет контроллер для представления "/user", который
 * отображает представление с именем "user". Это упрощает настройку
 * маршрута для пользователя без создания дополнительного контроллера.
 *
 * Реализует WebMvcConfigurer для настройки MVC без создания нового контроллера.
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    // Метод добавляет маршрут для /user, который направляет к представлению "user".
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/user").setViewName("user"); // Настройка пути /user к user.html
    }
}

