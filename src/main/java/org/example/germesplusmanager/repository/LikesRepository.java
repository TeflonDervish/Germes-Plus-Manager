package org.example.germesplusmanager.repository;

import org.example.germesplusmanager.model.Likes;
import org.example.germesplusmanager.model.persons.IndividualPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    @Query("SELECT l " +
            "FROM Likes l " +
            "WHERE l.productForIndividual.id = :productId AND l.individualPerson.id = :userId")
    Optional<Likes> checkIsLiked(Long productId, Long userId);

    List<Likes> findByIndividualPerson(IndividualPerson individualPerson);

}
