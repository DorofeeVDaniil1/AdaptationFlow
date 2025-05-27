package com.project.adaptationflow.config;

import com.project.adaptationflow.entity.person.Role;
import com.project.adaptationflow.entity.person.SysUser;
import com.project.adaptationflow.entity.person.SysUserRole;
import com.project.adaptationflow.repo.SysUserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final SysUserRepository userRepository;

    public CustomUserDetailsService(SysUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        SysUser user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPasswordHash(),
                user.getSysUserRoles().stream()
                        .map(SysUserRole::getRole)                  // получаем Role из связки
                        .map(Role::getName)                         // имя роли, например "ROLE_ADMIN"
                        .map(SimpleGrantedAuthority::new)          // преобразуем в GrantedAuthority
                        .collect(Collectors.toList())
        );
    }
}
