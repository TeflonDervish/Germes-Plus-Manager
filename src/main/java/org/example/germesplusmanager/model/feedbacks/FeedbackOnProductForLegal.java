package org.example.germesplusmanager.model.feedbacks;

import jakarta.persistence.*;
import lombok.*;
import org.example.germesplusmanager.model.persons.LegalPerson;
import org.example.germesplusmanager.model.products.ProductForLegal;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackOnProductForLegal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ProductForLegal productForLegal;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private LegalPerson legalPerson;

    @Column(columnDefinition = "TEXT")
    private String text;

    private Double grade;

    @ElementCollection
    @CollectionTable(name="urlsFeedbackForProductForLegal", joinColumns = @JoinColumn(name="id"))
    @Column(name = "urls")
    private List<String> urls;

}
