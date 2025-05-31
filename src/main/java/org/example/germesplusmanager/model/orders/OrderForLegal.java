package org.example.germesplusmanager.model.orders;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.germesplusmanager.model.Fabric;
import org.example.germesplusmanager.model.persons.FabricManager;
import org.example.germesplusmanager.model.persons.LegalPerson;
import org.example.germesplusmanager.model.products.ProductForLegal;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class OrderForLegal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "legal_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private LegalPerson legalPerson;

    @ElementCollection
    @CollectionTable(name = "orderForLegalProduct", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "products")
    private List<ProductForLegal> products;

    private Integer totalPrice;

    @ManyToOne
    @JoinColumn(name = "fabric_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Fabric fabric;

    @ManyToOne
    @JoinColumn(name = "fabric_manager_order_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private FabricManager fabricManager;

}
