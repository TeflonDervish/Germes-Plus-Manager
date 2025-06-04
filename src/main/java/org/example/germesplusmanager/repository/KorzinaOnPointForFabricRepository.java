package org.example.germesplusmanager.repository;

import org.example.germesplusmanager.model.korzina.KorzinaOnPointForFabric;
import org.example.germesplusmanager.model.korzina.KorzinaOnPointOfSale;
import org.example.germesplusmanager.model.persons.PointManager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KorzinaOnPointForFabricRepository extends JpaRepository<KorzinaOnPointForFabric, Long> {

  KorzinaOnPointForFabric findByPointManager(PointManager pointManager);
}