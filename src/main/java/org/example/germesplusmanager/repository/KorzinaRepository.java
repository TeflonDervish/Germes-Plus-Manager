package org.example.germesplusmanager.repository;

import org.example.germesplusmanager.model.Korzina;
import org.example.germesplusmanager.model.persons.IndividualPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KorzinaRepository extends JpaRepository<Korzina, Long> {

    Korzina findByIndividualPerson(IndividualPerson individualPerson);
}
