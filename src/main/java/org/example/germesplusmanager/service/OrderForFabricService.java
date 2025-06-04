package org.example.germesplusmanager.service;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.germesplusmanager.enums.OrderStatus;
import org.example.germesplusmanager.model.Fabric;
import org.example.germesplusmanager.model.PointOfSale;
import org.example.germesplusmanager.model.korzina.KorzinaOnPointForFabric;
import org.example.germesplusmanager.model.orders.OrderForFabric;
import org.example.germesplusmanager.model.orders.OrderForIndividual;
import org.example.germesplusmanager.model.persons.PointManager;
import org.example.germesplusmanager.repository.OrderForFabricRepository;
import org.hibernate.query.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderForFabricService {

    private static final Log log = LogFactory.getLog(OrderForFabricService.class);

    private final OrderForFabricRepository orderForFabricRepository;

    private final KorzinaOnPointForFabricService korzinaOnPointForFabricService;

    private final FabricService fabricService;

//    private final KorzinaService korzinaService;
//    private final PointOfSaleService pointOfSaleService;

//    @Transactional
//    public OrderForIndividual createOrder(IndividualPerson user, OrderDto orderDto) {
//        log.info("Создание заказа для " + user.getEmail());
//
//        Korzina korzina = korzinaService.getKorzina(user);
//
//        OrderForIndividual order = new OrderForIndividual();
//        order.setIndividualPerson(user);
//        order.setProducts(new ArrayList<>(korzina.getProducts()));
//        order.setOrderDate(LocalDate.now());
//        order.setStatus(OrderStatus.WAITING_ACCESS);
//
//        Integer totalPrice = 0;
//        for (ProductForIndividual product : korzina.getProducts())
//            totalPrice += product.getPrice();
//
//        order.setTotalPrice(totalPrice);
//
//        if (orderDto.getDeliveryType().equals("delivery")) {
//            processDelivery(order, orderDto.getDeliveryDetails());
//        } else {
//            processPickup(order, orderDto.getPickupDetails());
//        }
//
//        OrderForIndividual savedOrder = orderForIndividualRepository.save(order);
//
//        korzinaService.clear(user);
//
//        return savedOrder;
//    }

//    private void processDelivery(OrderForIndividual order, DeliveryDetailsDto details) {
//        log.info("Заказ с доставкой");
//        order.setDeliveryType(DeliveryType.DELIVERY);
//        order.setDeliveryAddress(details.getAddress());
//    }

//    private void processPickup(OrderForIndividual order, PickupDetailsDto details) {
//        log.info("Самовывоз");
//        order.setDeliveryType(DeliveryType.PICKUP);
//
//        PointOfSale pointOfSale = pointOfSaleService.getById(details.getPickupPointId());
//
//        order.setPointOfSale(pointOfSale);
//        order.setOrderDate(details.getPickupDate());
//    }

    public OrderForFabric save(OrderForFabric order) {
        return orderForFabricRepository.save(order);
    }

    public List<OrderForFabric> getByFabric(Fabric fabric) {

        log.info("Получение заказов пользователя");
        return orderForFabricRepository.findByFabricId(fabric.getId());
    }

    public List<OrderForFabric> getAll() {
        log.info("Получение списка всех заказов");
        return orderForFabricRepository.findAll();
    }

    public OrderForFabric getById(Long id) {
        return orderForFabricRepository.findById(id).orElse(null);
    }

    public OrderForFabric changeOrderStatus(Long id, OrderStatus status, PointManager manager) {
        log.info("Смена статуса заказа " + status);
        OrderForFabric order = getById(id);
        order.setStatus(status);
        return save(order);
    }

    public List<OrderForFabric> getByStatus(OrderStatus status) {
        log.info("Поиск по статусу " + status);
        return orderForFabricRepository.findByStatus(status);
    }

    public List<OrderForFabric> getByPointOfSale(PointOfSale pointOfSale) {
        log.info("Выдача заказов для " + pointOfSale.getId());
        return orderForFabricRepository.findByPointOfSale_Id(pointOfSale.getId());
    }

    public OrderForFabric createOrder(Long fabricId, PointManager manager) {
        log.info("Создание заказа");
        KorzinaOnPointForFabric korzina = korzinaOnPointForFabricService.getKorzina(manager);
        OrderForFabric order = OrderForFabric.builder()
                .orderDate(LocalDate.now())
                .pointOfSale(manager.getPointOfSale())
                .fabric(fabricService.getById(fabricId))
                .products(new ArrayList<>(korzina.getProducts()))
                .totalPrice(korzina.getTotalPrice())
                .build();
        korzina.getProducts().clear();
        return orderForFabricRepository.save(order);
    }

    public List<OrderForFabric> getByDateBetween(LocalDate startDate, LocalDate endDate, Fabric fabric) {
        return orderForFabricRepository.findByOrderDateBetweenAndFabricId(startDate, endDate, fabric.getId());
    }
}
