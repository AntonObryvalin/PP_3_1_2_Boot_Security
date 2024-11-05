package ru.kata.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Класс конфигурации безопасности WebSecurityConfig для настройки Spring Security.
 *
 * Этот класс задает правила доступа к ресурсам, настройки аутентификации,
 * обработку успешной аутентификации и создает пользователей в памяти.
 *
 * Конфигурация активирует форму входа и настраивает ее с SuccessUserHandler
 * для определения страницы перенаправления.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SuccessUserHandler successUserHandler;

    // Внедрение SuccessUserHandler для управления перенаправлением после входа
    public WebSecurityConfig(SuccessUserHandler successUserHandler) {
        this.successUserHandler = successUserHandler;
    }

    // Настройка правил доступа и параметров входа/выхода из системы
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() // Настройка правил авторизации
                .antMatchers("/", "/index").permitAll() // Доступ к / и /index для всех
                .anyRequest().authenticated() // Все остальные запросы требуют аутентификации
                .and()
                .formLogin() // Включение формы входа
                .successHandler(successUserHandler) // Обработка успешного входа через SuccessUserHandler
                .permitAll() // Разрешение на доступ к странице входа для всех
                .and()
                .logout() // Настройка выхода
                .permitAll(); // Разрешение на доступ к странице выхода для всех
    }

    // Настройка пользователей в памяти для тестирования аутентификации
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        // Создание пользователя с ролью USER и паролем "user"
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("USER")
                .build();

        // Возвращаем UserDetailsService с созданным пользователем
        return new InMemoryUserDetailsManager(user);
    }
}
