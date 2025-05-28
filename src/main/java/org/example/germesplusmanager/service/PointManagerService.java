package org.example.germesplusmanager.service;

import lombok.AllArgsConstructor;
import org.example.germesplusmanager.repository.PointManagerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PointManagerService implements UserDetailsService {

    private final PointManagerRepository pointManagerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return pointManagerRepository.findByEmail(username);
    }
}
