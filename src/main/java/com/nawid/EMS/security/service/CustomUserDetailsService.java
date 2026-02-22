package com.nawid.EMS.security.service;




import com.nawid.EMS.security.user.CustomUserPrincipal;
import com.nawid.EMS.security.user.repo.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        var user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));


        var authorities = List.of(
                new SimpleGrantedAuthority(user.getRole().name())
        );

        Long employeeId = user.getEmployee() != null
                ? user.getEmployee().getId()
                : null;

        return new CustomUserPrincipal(
                user.getId(),
                employeeId,
                user.getUsername(),
                user.getPassword(),
                authorities
        );



//        return org.springframework.security.core.userdetails.User
//                .builder()
//                .username(user.getUsername())
//                .password(user.getPassword())
//                .roles(user.getRole().name().replace("ROLE_", ""))
//                .build();
    }

}