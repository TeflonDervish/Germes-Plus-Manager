package org.example.germesplusmanager.service;

import lombok.AllArgsConstructor;
import org.example.germesplusmanager.enums.Role;
import org.example.germesplusmanager.model.persons.PointManager;
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

    public PointManager createManager(PointManager reg, PointManager current) {
        PointManager newManger = PointManager.builder()
                .surname(reg.getSurname())
                .name(reg.getName())
                .email(reg.getName())
                .phoneNumber(reg.getPhoneNumber())
                .pointOfSale(current.getPointOfSale())
                .role(Role.USER)
                .password(reg.getPassword())
                .build();
        return pointManagerRepository.save(newManger);
    }
}
