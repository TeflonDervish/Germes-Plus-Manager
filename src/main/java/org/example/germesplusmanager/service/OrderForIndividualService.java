package org.example.germesplusmanager.service;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.germesplusmanager.dto.OrderForIndividualDto;
import org.example.germesplusmanager.enums.DeliveryType;
import org.example.germesplusmanager.enums.OrderStatus;
import org.example.germesplusmanager.model.PointOfSale;
import org.example.germesplusmanager.model.korzina.KorzinaOnPointOfSale;
import org.example.germesplusmanager.model.orders.OrderForIndividual;
import org.example.germesplusmanager.model.persons.IndividualPerson;
import org.example.germesplusmanager.model.persons.PointManager;
import org.example.germesplusmanager.repository.OrderForIndividualRepository;
import org.hibernate.query.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderForIndividualService {

    private static final Log log = LogFactory.getLog(OrderForIndividualService.class);

    private final OrderForIndividualRepository orderForIndividualRepository;

    private final KorzinaOnPointService korzinaOnPointService;

    private final PointOfSaleService pointOfSaleService;

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

    public OrderForIndividual save(OrderForIndividual orderForIndividual) {
        return orderForIndividualRepository.save(orderForIndividual);
    }

    public List<OrderForIndividual> getOrderForIndividualPerson(IndividualPerson user) {

        log.info("Получение заказов пользователя");
        return orderForIndividualRepository.findByIndividualPersonId(user.getId());
    }

    public List<OrderForIndividual> getAll() {
        log.info("Получение списка всех заказов");
        return orderForIndividualRepository.findAll();
    }

    public OrderForIndividual getById(Long id) {
        return orderForIndividualRepository.findById(id).orElse(null);
    }

    public OrderForIndividual changeOrderStatus(Long id, OrderStatus status, PointManager manager) {
        log.info("Смена статуса заказа " + status);
        OrderForIndividual orderForIndividual = getById(id);
        orderForIndividual.setPointManager(manager);
        orderForIndividual.setPointOfSale(manager.getPointOfSale());
        orderForIndividual.setStatus(status);
        return save(orderForIndividual);
    }

    public  List<OrderForIndividual> getByStatus(OrderStatus status) {
        log.info("Поиск по статусу " + status);
        return orderForIndividualRepository.findByStatus(status);
    }

    public List<OrderForIndividual> getByDeliveryType(DeliveryType deliveryType) {
        log.info("Поиск по типу доставки " + deliveryType);
        return orderForIndividualRepository.findByDeliveryType(deliveryType);
    }

    public List<OrderForIndividual> getByPointOfSale(PointOfSale pointOfSale) {
        log.info("Выдача заказов для " + pointOfSale.getId());
        return orderForIndividualRepository.findByPointOfSale_Id(pointOfSale.getId());
    }

    public OrderForIndividual createOrder(OrderForIndividualDto orderDto, IndividualPerson individualPerson, PointManager manager) {
        log.info("Создание заказа");
        KorzinaOnPointOfSale korzina = korzinaOnPointService.getKorzina(manager);
        OrderForIndividual order = OrderForIndividual.builder()
                .orderDate(LocalDate.now())
                .individualPerson(individualPerson)
                .products(new ArrayList<>(korzina.getProducts()))
                .totalPrice(korzina.getTotalPrice())
                .status(OrderStatus.WAITING_ACCESS)
                .pointOfSale(manager.getPointOfSale())
                .pointManager(manager)
                .build();
        korzina.getProducts().clear();
        if (orderDto.getDeliveryType().equals("delivery")){
            order.setDeliveryPrice(orderDto.getDeliveryPrice());
            order.setDeliveryAddress(orderDto.getCity() + " " + orderDto.getAddress());
            order.setDeliveryType(DeliveryType.DELIVERY);
        }
        else {
            order.setDeliveryType(DeliveryType.PICKUP);
            order.setPointOfSale(pointOfSaleService.getById(orderDto.getPointId()));
        }
        return orderForIndividualRepository.save(order);
    }

    public List<OrderForIndividual> getByDateBetween(LocalDate startDate, LocalDate endDate, PointOfSale pointOfSale) {
        return orderForIndividualRepository.findByOrderDateBetweenAndPointOfSaleId(startDate, endDate, pointOfSale.getId());
    }
}
