package org.example.germesplusmanager.model.othcet;


import jakarta.persistence.*;
import lombok.*;
import org.example.germesplusmanager.model.Fabric;
import org.example.germesplusmanager.model.products.ProductForIndividual;
import org.example.germesplusmanager.model.products.ProductForLegal;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OtchetForFabric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "otchets_for_fabric_individual",
            joinColumns = @JoinColumn(name = "otchet_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private List<ProductForIndividual> productsForIndividuals;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "otchets_for_fabric_legal",
            joinColumns = @JoinColumn(name = "otchet_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private List<ProductForLegal> productsForLegals;

    @ManyToOne
    @JoinColumn(name = "fabric_otchet_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Fabric fabric;

    @Column(length = 100)
    private String name;
    private String description;

    private Integer totalPrice;
    
}
