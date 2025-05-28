package org.example.germesplusmanager.model.orders;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.germesplusmanager.enums.OrderStatus;
import org.example.germesplusmanager.model.Fabric;
import org.example.germesplusmanager.model.PointOfSale;
import org.example.germesplusmanager.model.summaries.SummaryForFabric;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderForFabric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "orderForFabric")
    private List<SummaryForFabric> summaryForFabrics;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PointOfSale pointOfSale;

    @ManyToOne
    @JoinColumn(name = "fabric_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Fabric fabric;

    private Integer price;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
