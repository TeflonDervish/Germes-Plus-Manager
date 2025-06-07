package org.example.germesplusmanager.service;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.germesplusmanager.enums.Role;
import org.example.germesplusmanager.model.persons.PointManager;
import org.example.germesplusmanager.repository.PointManagerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PointManagerService implements UserDetailsService {

    private static final Log log = LogFactory.getLog(PointManagerService.class);
    private final PointManagerRepository pointManagerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return pointManagerRepository.findByEmail(username);
    }

    public PointManager createManager(PointManager reg, PointManager current) {
        if (pointManagerRepository.existsByEmail(reg.getEmail())) {
            log.warn("Пользователь с таким email уже существует");
            return null;
        }
        PointManager newManger = PointManager.builder()
                .surname(reg.getSurname())
                .name(reg.getName())
                .email(reg.getEmail())
                .phoneNumber(reg.getPhoneNumber())
                .pointOfSale(current.getPointOfSale())
                .role(Role.USER)
                .password(passwordEncoder.encode(reg.getPassword()))
                .build();
        return pointManagerRepository.save(newManger);
    }
}
