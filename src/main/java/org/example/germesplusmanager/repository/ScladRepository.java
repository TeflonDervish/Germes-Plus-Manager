package org.example.germesplusmanager.repository;

import org.example.germesplusmanager.model.PointOfSale;
import org.example.germesplusmanager.model.Sclad;
import org.example.germesplusmanager.model.products.ProductForIndividual;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScladRepository extends JpaRepository<Sclad, Long> {

    List<Sclad> findByPointOfSale(PointOfSale pointOfSale);

    Sclad findByPointOfSaleAndProductForIndividual(PointOfSale pointOfSale, ProductForIndividual productForIndividual);

    List<Sclad> findByProductForIndividual_NameContaining(String name);

}
