package org.example.germesplusmanager.model.summaries;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.germesplusmanager.model.PointOfSale;
import org.example.germesplusmanager.model.orders.OrderForFabric;
import org.example.germesplusmanager.model.products.ProductForIndividual;

@Entity
@Data
@NoArgsConstructor
public class SummaryForFabric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "point_id")
    private PointOfSale pointOfSale;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductForIndividual productForIndividual;

    @ManyToOne
    @JoinColumn(name = "order_for_fabric_id")
    private OrderForFabric orderForFabric;

    private Integer quantity;

}
