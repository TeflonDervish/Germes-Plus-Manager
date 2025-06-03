package org.example.germesplusmanager.repository;

import org.example.germesplusmanager.model.othcet.OtchetForPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OtchetForPointRepository extends JpaRepository<OtchetForPoint, Long> {

    List<OtchetForPoint> findByPointOfSaleId(Long pointOfSaleId);
}