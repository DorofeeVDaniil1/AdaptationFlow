package com.project.adaptationflow.config;

import com.project.adaptationflow.entity.user.Role;
import com.project.adaptationflow.entity.user.SysUser;
import com.project.adaptationflow.entity.user.SysUserRoleLink;
import com.project.adaptationflow.repo.SysUserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final SysUserRepository userRepository;

    public CustomUserDetailsService(SysUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        // Загружаем роли через join-сущность
        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map(SysUserRoleLink::getRole)                  // достаём Role
                .map(Role::getName)                             // достаём имя роли
                .map(SimpleGrantedAuthority::new)               // превращаем в GrantedAuthority
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPasswordHash(),
                user.getIsActive(),
                true, true, true,
                authorities);
    }

}
