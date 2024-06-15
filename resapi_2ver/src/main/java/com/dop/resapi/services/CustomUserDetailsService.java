package com.dop.resapi.services;

import com.dop.resapi.models.User;
import com.dop.resapi.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails. UserDetailsService; import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util. List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    public CustomUserDetailsService(UserRepository repository) { userRepository = repository; }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        User user = userRepository.findUserByEmail(email);
        List<String> roles = new ArrayList<>();
        roles.add("USER");

        UserDetails userDetails = org.springframework.security.core.userdetails. User.builder()
                .username(user.getName()) .password(user.getPassword())
                .roles(roles.toArray(new String[0]))
                .build();
        return userDetails;
    }
}
