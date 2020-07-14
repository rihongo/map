package com.rihongo.map.config.handler;

import com.rihongo.map.model.UserSession;
import com.rihongo.map.model.entities.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final ModelMapper modelMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        HttpSession session = request.getSession(true);

        if (session != null) {
            User user = (User) authentication.getPrincipal();
            UserSession userSession = modelMapper.map(user, UserSession.class);
            session.setAttribute("user", userSession);
        }
        response.sendRedirect("/map");
    }
}
