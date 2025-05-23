package com.baeldung.web.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/custom")
public class LoginController {

    @Resource(name="authenticationManager")
    private AuthenticationManager authManager;

    public LoginController() {
        super();
    }

    // API

    // custom login

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(@RequestParam("username") final String username, @RequestParam("password") final String password, final HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authReq =
            new UsernamePasswordAuthenticationToken(username, password);
        Authentication auth = authManager.authenticate(authReq);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", sc);
    }

}