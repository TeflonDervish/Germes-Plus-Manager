package org.example.germesplusmanager.model.summaries;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.germesplusmanager.model.orders.OrderForLegal;
import org.example.germesplusmanager.model.persons.LegalPerson;
import org.example.germesplusmanager.model.products.ProductForLegal;

@Entity
@Data
@NoArgsConstructor
public class SummaryForLegal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private LegalPerson legalPerson;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductForLegal productForLegal;

    @ManyToOne
    @JoinColumn(name = "order_legal_id")
    private OrderForLegal orderForLegal;

    private Integer quantity;

}
