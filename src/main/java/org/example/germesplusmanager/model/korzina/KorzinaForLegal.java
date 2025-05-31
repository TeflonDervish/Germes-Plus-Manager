package org.example.germesplusmanager.model.korzina;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.germesplusmanager.model.persons.IndividualPerson;
import org.example.germesplusmanager.model.products.ProductForLegal;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class KorzinaForLegal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private IndividualPerson individualPerson;

    @ElementCollection
    @CollectionTable(name = "korzinaProductForLegal", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "products")
    private List<ProductForLegal> products;

    public void addProduct(ProductForLegal product) {
        products.add(product);
    }

    public void deleteProduct(ProductForLegal product) {
        products.remove(product);
    }

    public boolean isInKorzina(ProductForLegal product) {
        return products.contains(product);
    }
}
