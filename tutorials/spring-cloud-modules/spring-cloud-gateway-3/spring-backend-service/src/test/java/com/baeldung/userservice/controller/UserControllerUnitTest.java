package com.baeldung.userservice.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void givenUserIsAvailable_whenGetUserCalled_thenReturnUserDetails() throws Exception {
        mockMvc.perform(get("/user/100001"))
            .andExpect(status().is(HttpStatus.OK.value()));
    }
}
