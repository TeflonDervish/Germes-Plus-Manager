package org.example.germesplusmanager.repository;

import org.example.germesplusmanager.model.feedbacks.FeedbackOnProductForIndividual;
import org.example.germesplusmanager.model.products.ProductForIndividual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackOnProductForIndividual, Long> {

    List<FeedbackOnProductForIndividual> findByProductForIndividual(ProductForIndividual productForIndividual);

}
