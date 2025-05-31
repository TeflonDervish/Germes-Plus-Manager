package org.example.germesplusmanager.model.feedbacks;

import jakarta.persistence.*;
import lombok.*;
import org.example.germesplusmanager.model.persons.IndividualPerson;
import org.example.germesplusmanager.model.products.ProductForIndividual;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter @Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackOnProductForIndividual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ProductForIndividual productForIndividual;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private IndividualPerson individualPerson;

    @Column(columnDefinition = "TEXT")
    private String text;

    private Double grade;

    private LocalDate date;

    @ElementCollection
    @CollectionTable(name="urlsForFeedbackProductForIndividuals", joinColumns = @JoinColumn(name="id"))
    @Column(name = "urls")
    private List<String> urls;

}
