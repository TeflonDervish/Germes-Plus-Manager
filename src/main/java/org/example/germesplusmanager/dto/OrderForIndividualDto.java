package org.example.germesplusmanager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class OrderForIndividualDto {
    private List<Long> productIds;
    private String deliveryType; // "delivery" или "pickup"
    private String city;
    private String address;
    private Long pointId;
    private Integer deliveryPrice;
}
