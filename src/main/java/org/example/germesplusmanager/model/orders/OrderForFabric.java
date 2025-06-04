package org.example.germesplusmanager.model.orders;

import jakarta.persistence.*;
import lombok.*;
import org.example.germesplusmanager.enums.OrderStatus;
import org.example.germesplusmanager.model.Fabric;
import org.example.germesplusmanager.model.PointOfSale;
import org.example.germesplusmanager.model.persons.FabricManager;
import org.example.germesplusmanager.model.products.ProductForIndividual;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderForFabric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "order_for_fabric_product",
            joinColumns = @JoinColumn(name = "order_ir"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<ProductForIndividual> products;

    private Integer totalPrice;

    @ManyToOne
    @JoinColumn(name = "point_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PointOfSale pointOfSale;

    @ManyToOne
    @JoinColumn(name = "fabric_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Fabric fabric;

    @ManyToOne
    @JoinColumn(name = "fabric_manager_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private FabricManager fabricManager;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDate orderDate;
}
