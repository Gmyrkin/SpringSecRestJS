package ru.spb.db.rest.spring.exmp315.SpringRestAPI.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.spb.db.rest.spring.exmp315.SpringRestAPI.models.User;

import java.io.IOException;
import java.util.Set;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ROLE_ADMIN")) {

            System.out.println("залогинился admin");

            httpServletResponse.sendRedirect("/users");


        } else if (roles.contains("ROLE_USER")) {

            System.out.println("залогинился user");


            httpServletResponse.sendRedirect("/user");
        }
        else {

            System.out.println("залогинился ХХХ");

            httpServletResponse.sendRedirect("/login");
        }
    }

}
