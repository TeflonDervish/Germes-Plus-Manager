package org.example.germesplusmanager.model.likes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.germesplusmanager.model.persons.LegalPerson;
import org.example.germesplusmanager.model.products.ProductForLegal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LikesForLegal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private LegalPerson legalPerson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private ProductForLegal productForLegal;
}
