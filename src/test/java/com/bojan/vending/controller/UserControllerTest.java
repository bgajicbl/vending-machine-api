package com.bojan.vending.controller;

import com.bojan.vending.data.RoleFactory;
import com.bojan.vending.data.UserDTOFactory;
import com.bojan.vending.dto.UserDto;
import com.bojan.vending.model.Role;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Set;

import static com.bojan.vending.util.JsonHelper.fromJson;
import static com.bojan.vending.util.JsonHelper.toJson;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
//@WithUserDetails("admin")
class UserControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserControllerTest(MockMvc mockMvc,
                         ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    void register()throws Exception{
        UserDto userDto = UserDTOFactory.createWithoutId();

        MvcResult createResult = this.mockMvc
                .perform(post("/api/v1/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(objectMapper, userDto)))
                .andExpect(status().isCreated())
                .andReturn();

        UserDto response = fromJson(objectMapper, createResult.getResponse().getContentAsString(), UserDto.class);
        assertNotNull(response.getId(), "User id must not be null!");

    }

    @Test
    void fetchUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}