package org.example.germesplusmanager.model.products;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@Entity
@Getter
@Setter
public class ProductForIndividual {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60)
    private String name;

    @Column(length = 60)
    private Integer price;

    @Column(length = 60)
    private String size;

    @Column(length = 60)
    private String article;

    @Column(length = 60) // Основа
    private String basis;

    @Column(length = 60) // Наполнение
    private String filling;

    @Column(length = 60) // Короб
    private String box;

    @Column(length = 60) // Механизм
    private String mechanism;

    @Column(length = 60) // Подлокотники
    private String armrests;

    @Column(length = 60) // Глубина сидушки
    private String seatDepth;

    @Column(length = 60) // Посадка
    private String planting;

    @Column(length = 60) // Общий габарит
    private String overallDimensions;

    @Column(length = 60) // Спальное место
    private String sleepingSpace;
  
    @Column(length = 60) // Глубина
    private String depth;

    @Column(length = 60) // Конфигурация
    private String configuration;



    @Lob
    private String description;

    @Column(columnDefinition = "json")
    private String characteristics;

    @ElementCollection
    @CollectionTable(name="urls", joinColumns = @JoinColumn(name="id"))
    @Column(name = "urls")
    private List<String> urls;

}
