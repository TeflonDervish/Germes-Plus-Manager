package org.example.germesplusmanager.model.summaries;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.germesplusmanager.model.orders.OrderForIndividual;
import org.example.germesplusmanager.model.persons.IndividualPerson;
import org.example.germesplusmanager.model.products.ProductForIndividual;

@Entity
@Data
@NoArgsConstructor
public class SummaryForIndividual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private IndividualPerson individualPerson;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductForIndividual productForIndividual;

    @ManyToOne
    @JoinColumn(name = "order_for_individual")
    private OrderForIndividual orderForIndividual;

    private Integer quantity;

}
