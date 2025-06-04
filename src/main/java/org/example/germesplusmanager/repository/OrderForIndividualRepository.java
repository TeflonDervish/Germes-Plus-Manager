package org.example.germesplusmanager.repository;

import org.example.germesplusmanager.enums.DeliveryType;
import org.example.germesplusmanager.enums.OrderStatus;
import org.example.germesplusmanager.model.PointOfSale;
import org.example.germesplusmanager.model.orders.OrderForIndividual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderForIndividualRepository extends JpaRepository<OrderForIndividual, Long> {

    List<OrderForIndividual> findByIndividualPersonId(Long id);

    List<OrderForIndividual> findByStatus(OrderStatus status);

    List<OrderForIndividual> findByDeliveryType(DeliveryType deliveryType);

    List<OrderForIndividual> findByPointOfSale_Id(Long id);

    List<OrderForIndividual> findByOrderDateBetween(LocalDate startDate, LocalDate endDate);

    List<OrderForIndividual> findByOrderDateBetweenAndPointOfSaleId(LocalDate orderDate, LocalDate orderDate2, Long pointOfSale_id);
}
