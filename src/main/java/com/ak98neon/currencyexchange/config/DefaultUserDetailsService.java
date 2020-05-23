package com.ak98neon.currencyexchange.config;

import com.ak98neon.currencyexchange.model.Role;
import com.ak98neon.currencyexchange.model.User;
import com.ak98neon.currencyexchange.model.service.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class DefaultUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;

    public DefaultUserDetailsService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public final UserDetails loadUserByUsername(final String login) {
        final User user = userRepository.findByUsername(login);
        Set<GrantedAuthority> roles = new HashSet<>();
        Set<Role> userRoles = user.getRoles();
        userRoles.forEach(role -> roles.add(new SimpleGrantedAuthority(role.getName().name())));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                roles);
    }
}
