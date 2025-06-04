package org.example.germesplusmanager.repository;

import org.example.germesplusmanager.enums.DeliveryType;
import org.example.germesplusmanager.enums.OrderStatus;
import org.example.germesplusmanager.model.orders.OrderForFabric;
import org.example.germesplusmanager.model.orders.OrderForIndividual;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrderForFabricRepository extends JpaRepository<OrderForFabric, Long> {

    List<OrderForFabric> findByFabricId(Long id);

    List<OrderForFabric> findByStatus(OrderStatus status);

    List<OrderForFabric> findByPointOfSale_Id(Long id);

    List<OrderForFabric> findByOrderDateBetween(LocalDate startDate, LocalDate endDate);

    List<OrderForFabric> findByOrderDateBetweenAndFabricId(LocalDate orderDate, LocalDate orderDate2, Long fabricId);
}