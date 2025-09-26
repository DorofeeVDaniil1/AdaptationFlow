package com.project.adaptationflow.controller;

import com.project.adaptationflow.dto.auth.AuthResponse;
import com.project.adaptationflow.dto.auth.UserLoginRequest;
import com.project.adaptationflow.dto.auth.UserRegistrationRequest;
import com.project.adaptationflow.entity.person.Role;
import com.project.adaptationflow.entity.person.SysUser;
import com.project.adaptationflow.entity.tasks.Task;
import com.project.adaptationflow.entity.tasks.UserTrackStage;
import com.project.adaptationflow.entity.tasks.UsersTasksLink;
import com.project.adaptationflow.repo.RoleRepository;
import com.project.adaptationflow.repo.TaskRepository;
import com.project.adaptationflow.service.SysUserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/api/auth")
public class UserRegistrationController {

    private final SysUserService sysUserService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final TaskRepository taskRepository;

    private SysUserService usersTasksLinkRepository;

    public UserRegistrationController(SysUserService sysUserService, RoleRepository roleRepository, PasswordEncoder passwordEncoder, TaskRepository taskRepository) {
        this.sysUserService = sysUserService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.taskRepository = taskRepository;
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
        Role studentRole = roleRepository.findByName("ROLE_USER")
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

