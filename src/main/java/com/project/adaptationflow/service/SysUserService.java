package com.project.adaptationflow.service;

import com.project.adaptationflow.config.JwtUtil;
import com.project.adaptationflow.entity.person.Role;
import com.project.adaptationflow.entity.person.SysUser;
import com.project.adaptationflow.entity.person.SysUserRole;
import com.project.adaptationflow.entity.person.SysUserRoleId;
import com.project.adaptationflow.repo.SysUserRepository;
import com.project.adaptationflow.repo.SysUserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.stream.Collectors;

@Service
public class SysUserService {

    private final SysUserRepository userRepository;
    private final SysUserRoleRepository userRoleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public SysUserService(SysUserRepository userRepository, SysUserRoleRepository userRoleRepository, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public boolean existsByLogin(String login) {
        return userRepository.findByLogin(login).isPresent();
    }

    public SysUser save(SysUser user) {
        return userRepository.save(user);
    }

    public void assignRole(SysUser user, Role role) {
        SysUserRole userRole = new SysUserRole();
        userRole.setId(new SysUserRoleId(user.getId(), role.getId()));
        userRole.setUser(user);
        userRole.setRole(role);
        userRole.setAssignedAt(OffsetDateTime.now());
        userRoleRepository.save(userRole);
    }

    public String authenticate(String login, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login, password)
        );
        // Получаем объект пользователя
        SysUser user = userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPasswordHash(),
                user.getSysUserRoles().stream()
                        .map(SysUserRole::getRole)
                        .map(Role::getName)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList())
        );

        return jwtUtil.generateToken(userDetails);
    }
}
