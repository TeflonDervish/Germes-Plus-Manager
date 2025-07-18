package org.example.germesplusmanager.repository;

import org.example.germesplusmanager.model.Fabric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FabricRepository extends JpaRepository<Fabric, Long> {
}
