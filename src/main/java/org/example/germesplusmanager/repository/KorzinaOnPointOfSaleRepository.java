package org.example.germesplusmanager.repository;

import org.example.germesplusmanager.model.korzina.KorzinaOnPointOfSale;
import org.example.germesplusmanager.model.persons.PointManager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KorzinaOnPointOfSaleRepository extends JpaRepository<KorzinaOnPointOfSale, Long> {

  KorzinaOnPointOfSale findByPointManager(PointManager pointManager);
}