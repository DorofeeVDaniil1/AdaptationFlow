package com.project.adaptationflow.controller;

import com.project.adaptationflow.dto.AuthResponse;
import com.project.adaptationflow.dto.UserLoginRequest;
import com.project.adaptationflow.dto.UserRegistrationRequest;
import com.project.adaptationflow.entity.person.Role;
import com.project.adaptationflow.entity.person.SysUser;
import com.project.adaptationflow.repo.RoleRepository;
import com.project.adaptationflow.service.SysUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class UserRegistrationController {

    private final SysUserService sysUserService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserRegistrationController(SysUserService sysUserService, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.sysUserService = sysUserService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationRequest request) {
        // Проверяем, что логин не занят
        if (sysUserService.existsByLogin(request.getLogin())) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "Login is already taken"));
        }

        // Хешируем пароль
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // Получаем роль Student
        Role studentRole = roleRepository.findByName("Student")
                .orElseThrow(() -> new RuntimeException("Role Student not found"));

        // Создаём пользователя
        SysUser user = SysUser.builder()
                .login(request.getLogin())
                .passwordHash(encodedPassword)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .isActive(true)
                .build();

        // Сохраняем пользователя
        SysUser savedUser = sysUserService.save(user);

        // Назначаем роль
        sysUserService.assignRole(savedUser, studentRole);

        return ResponseEntity.ok(Map.of("message", "User registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody UserLoginRequest request) {
        String jwtToken = sysUserService.authenticate(request.getLogin(), request.getPassword());
        return ResponseEntity.ok(new AuthResponse(jwtToken));
    }

}