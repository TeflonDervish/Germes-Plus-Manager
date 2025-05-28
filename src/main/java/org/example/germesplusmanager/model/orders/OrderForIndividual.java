package org.example.germesplusmanager.model.orders;

import jakarta.persistence.*;
import lombok.*;
import org.example.germesplusmanager.enums.DeliveryType;
import org.example.germesplusmanager.enums.OrderStatus;
import org.example.germesplusmanager.model.PointOfSale;
import org.example.germesplusmanager.model.ShippingInformation;
import org.example.germesplusmanager.model.persons.IndividualPerson;
import org.example.germesplusmanager.model.products.ProductForIndividual;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
@ToString
public class OrderForIndividual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private IndividualPerson individualPerson;

    @ManyToOne
    @JoinColumn(name = "point_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PointOfSale pointOfSale;

    @ManyToOne
    @JoinColumn(name = "shipping_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ShippingInformation shippingInformation;

    @ElementCollection
    @CollectionTable(name = "orderForIndividualProduct", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "products")
    private List<ProductForIndividual> products;

    private LocalDate orderDate;

    private Integer totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;

    private String deliveryAddress;
}
