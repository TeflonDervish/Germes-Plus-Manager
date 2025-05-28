package org.example.germesplusmanager.model.orders;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.germesplusmanager.enums.OrderStatus;
import org.example.germesplusmanager.model.PointOfSale;
import org.example.germesplusmanager.model.persons.LegalPerson;
import org.example.germesplusmanager.model.summaries.SummaryForLegal;
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

    @OneToMany(mappedBy = "orderForLegal")
    private List<SummaryForLegal> summaryForLegals;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private LegalPerson legalPerson;

    @ManyToOne
    @JoinColumn(name = "point_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PointOfSale pointOfSale;

    private Integer price;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

}
