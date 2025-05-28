package org.example.germesplusmanager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class FilterProductForIndividual {
    private Integer minPrice;
    private Integer maxPrice;
    private List<String> configurations;
    private List<String> mechanisms;
    private Integer minSleepingLength;
    private Integer maxSleepingLength;
    private Integer minSleepingWidth;
    private Integer maxSleepingWidth;
    private List<String> fillings;
}
