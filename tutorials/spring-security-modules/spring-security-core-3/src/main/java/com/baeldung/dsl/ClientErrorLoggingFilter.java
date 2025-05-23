package com.baeldung.dsl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.List;

public class ClientErrorLoggingFilter extends GenericFilterBean {

    private static final Logger logger = LogManager.getLogger(ClientErrorLoggingFilter.class);

    private List<HttpStatus> errorCodes;

    public ClientErrorLoggingFilter(List<HttpStatus> errorCodes) {
        this.errorCodes = errorCodes;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        Authentication auth = SecurityContextHolder.getContext()
            .getAuthentication();

        if (auth == null) {
            chain.doFilter(request, response);
            return;
        }
        int status = ((HttpServletResponse) response).getStatus();
        if (status < 400 || status >= 500) {
            chain.doFilter(request, response);
            return;
        }

        if (errorCodes == null) {
            logger.debug("User " + auth.getName() + " encountered error " + status);
        } else {
            if (errorCodes.stream()
                .anyMatch(s -> s.value() == status)) {
                logger.debug("User " + auth.getName() + " encountered error " + status);
            }
        }

        chain.doFilter(request, response);
    }

}
