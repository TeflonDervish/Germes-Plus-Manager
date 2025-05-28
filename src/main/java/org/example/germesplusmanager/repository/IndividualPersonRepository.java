package org.example.germesplusmanager.repository;

import org.example.germesplusmanager.model.persons.IndividualPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IndividualPersonRepository extends JpaRepository<IndividualPerson, Long> {

    Optional<IndividualPerson> findByEmail(String email);

    boolean existsByEmail(String email);
}
