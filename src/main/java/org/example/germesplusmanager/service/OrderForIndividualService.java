package org.example.germesplusmanager.service;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.germesplusmanager.enums.OrderStatus;
import org.example.germesplusmanager.model.orders.OrderForIndividual;
import org.example.germesplusmanager.model.persons.IndividualPerson;
import org.example.germesplusmanager.repository.OrderForIndividualRepository;
import org.hibernate.query.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderForIndividualService {

    private static final Log log = LogFactory.getLog(OrderForIndividualService.class);

    private final OrderForIndividualRepository orderForIndividualRepository;
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

    public OrderForIndividual changeOrderStatus(Long id, OrderStatus status) {
        log.info("Смена статуса заказа " + status);
        OrderForIndividual orderForIndividual = getById(id);
        orderForIndividual.setStatus(status);
        return save(orderForIndividual);
    }
}
