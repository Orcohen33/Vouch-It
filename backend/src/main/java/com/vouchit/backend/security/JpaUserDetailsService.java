package com.vouchit.backend.security;

import com.vouchit.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * When spring security is used, it needs to know how to load the user from the database.
 * This class is used to load the user from the database.
 */
@Service
@AllArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        var u = userRepository.findByEmail(username);
        if (u.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return u.map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: "+username));
    }
}
