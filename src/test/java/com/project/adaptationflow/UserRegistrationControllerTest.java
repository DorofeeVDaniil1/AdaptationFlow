package com.project.adaptationflow;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.adaptationflow.config.JwtAuthenticationFilter;
import com.project.adaptationflow.config.JwtUtil;
import com.project.adaptationflow.controller.UserRegistrationController;
import com.project.adaptationflow.dto.AuthResponse;
import com.project.adaptationflow.dto.UserLoginRequest;
import com.project.adaptationflow.dto.UserRegistrationRequest;
import com.project.adaptationflow.repo.RoleRepository;
import com.project.adaptationflow.service.SysUserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserRegistrationController.class)
@AutoConfigureMockMvc(addFilters = false) // отключаем все security фильтры
class UserRegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtUtil jwtUtil;

    // Замокаем все зависимости контроллера
    @MockitoBean
    private SysUserService sysUserService;

    @MockitoBean
    private RoleRepository roleRepository;
    @MockitoBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;


    @MockitoBean
    private PasswordEncoder passwordEncoder;
    @Test
    @DisplayName("Register: 200 OK with real JWT token")
    void registerUser_ShouldReturnRealJwtToken() throws Exception {
        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setLogin("test");
        request.setPassword("dandy_200325");
        request.setFirstName("dan");
        request.setLastName("dor");
        request.setEmail("dan@gmail.com");
        request.setRole("USER");

        // Мокаем регистрацию (void)
        Mockito.doNothing().when(sysUserService).userRegister(Mockito.any());

        // Генерация реального JWT токена
        Mockito.when(sysUserService.authenticate(Mockito.eq("test"), Mockito.eq("dandy_200325")))
                .thenAnswer(invocation -> {
                    UserDetails user = org.springframework.security.core.userdetails.User
                            .withUsername("test")
                            .password("dandy_200325")
                            .roles("USER")
                            .build();
                    return jwtUtil.generateToken(user);
                });

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists()); // проверяем, что токен реально вернулся
    }



    @Test
    @DisplayName("Login: 200 OK with JWT token")
    void login_ShouldReturnToken() throws Exception {
        UserLoginRequest request = new UserLoginRequest();
        request.setLogin("test");
        request.setPassword("dandy_200325");

        AuthResponse authResponse = new AuthResponse("jwt-token-123");

        Mockito.when(sysUserService.authenticate(Mockito.anyString(), Mockito.anyString()))
                .thenReturn("jwt-token-123");

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token", is("jwt-token-123")));
    }

    @Test
    @DisplayName("Register: 400 Bad Request on exception")
    void registerUser_ShouldReturnBadRequest() throws Exception {
        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setLogin("test");

        Mockito.doThrow(new RuntimeException("User already exists"))
                .when(sysUserService).userRegister(Mockito.any());

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is("User already exists")));
    }
}
