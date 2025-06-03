package org.example.germesplusmanager.repository;

import org.example.germesplusmanager.model.PointOfSale;
import org.example.germesplusmanager.model.feedbacks.FeedbackOnPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackForPointRepository extends JpaRepository<FeedbackOnPoint, Long> {

    List<FeedbackOnPoint> findByPointOfSale(PointOfSale pointOfSale);

}
