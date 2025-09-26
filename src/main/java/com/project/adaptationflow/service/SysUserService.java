package com.project.adaptationflow.service;

import com.project.adaptationflow.config.JwtUtil;
import com.project.adaptationflow.dto.UserRegistrationRequest;
import com.project.adaptationflow.entity.user.Role;
import com.project.adaptationflow.entity.user.SysUser;
import com.project.adaptationflow.entity.user.SysUserRoleLink;
import com.project.adaptationflow.entity.user.SysUserRoleLinkId;
import com.project.adaptationflow.repo.RoleRepository;
import com.project.adaptationflow.repo.SysUserRepository;
import com.project.adaptationflow.repo.SysUserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SysUserService {

    private final SysUserRepository userRepository;
    private final SysUserRoleRepository sysUserRoleRepository;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public boolean existsByLogin(String login) {
        return userRepository.findByLogin(login).isPresent();
    }

    public SysUser save(SysUser user) {
        return userRepository.save(user);
    }

    public void assignRole(SysUser user, Role role) {
        SysUserRoleLink link = new SysUserRoleLink();
        SysUserRoleLinkId id = new SysUserRoleLinkId();
        id.setUserId(user.getId());
        id.setRoleId(role.getId());
        link.setId(id);
        link.setUser(user);
        link.setRole(role);
        sysUserRoleRepository.save(link);
    }


    public SysUser userRegister(UserRegistrationRequest request) {
        // Check if user already exists
        if (existsByLogin(request.getLogin())) {
            throw new RuntimeException("User with login " + request.getLogin() + " already exists");
        }

        // Encode password
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // Create user
        SysUser user = new SysUser();
        user.setLogin(request.getLogin());
        user.setPasswordHash(encodedPassword);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setIsActive(true);

        // Save user
        SysUser savedUser = save(user);

        // Assign role
        Optional<Role> role = roleRepository.findByName(request.getRole());
        if (role.isPresent()) {
            assignRole(savedUser, role.get());
        } else {
            throw new RuntimeException("Role not found: " + request.getRole());
        }
        return savedUser;
    }

    public String authenticate(String login, String password) {
        // Проверка пароля через AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login, password)
        );

        // Получаем пользователя с подгруженными ролями
        SysUser user = userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Собираем роли через join-сущность
        var authorities = user.getRoles().stream()
                .map(SysUserRoleLink::getRole)          // достаём объект Role
                .map(Role::getName)                     // берём имя роли
                .map(SimpleGrantedAuthority::new)       // превращаем в GrantedAuthority
                .collect(Collectors.toList());

        // Создаём объект UserDetails для Spring Security / JWT
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPasswordHash(),
                user.getIsActive(), // enabled
                true, true, true,   // accountNonExpired, credentialsNonExpired, accountNonLocked
                authorities
        );

        // Генерируем JWT
        return jwtUtil.generateToken(userDetails);
    }

}
