package ru.kata.spring.boot_security.demo.configs;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class SuccessUserHandler implements AuthenticationSuccessHandler {

    // Метод обрабатывает успешную аутентификацию, перенаправляя пользователя в зависимости от его роли.
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        // Получение ролей авторизованного пользователя
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        // Если роль USER, перенаправляем на /user, иначе на /
        if (roles.contains("ROLE_USER")) {
            httpServletResponse.sendRedirect("/user");
        } else {
            httpServletResponse.sendRedirect("/");
        }
    }
}