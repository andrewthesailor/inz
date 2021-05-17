package com.szczepanika.scheduler.security;

import com.szczepanika.scheduler.model.parameters.Teacher;
import com.szczepanika.scheduler.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TeacherService teacherService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        String login = token.getName();
        String password = (String)token.getCredentials();
        Teacher teacher = (Teacher) teacherService.loadUserByUsername(login);
        String encodedPassword = passwordEncoder.encode(password);

        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
