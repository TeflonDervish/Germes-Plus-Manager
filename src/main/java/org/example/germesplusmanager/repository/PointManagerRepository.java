package org.example.germesplusmanager.repository;

import org.example.germesplusmanager.model.persons.PointManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface PointManagerRepository extends JpaRepository<PointManager, Long> {

    boolean existsByEmail(String email);

    UserDetails findByEmail(String email);
}
