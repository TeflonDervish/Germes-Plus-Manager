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
public class OrderDto {
    private List<Long> productIds;
    private String deliveryType; // "delivery" или "pickup"
    private DeliveryDetailsDto deliveryDetails;
    private PickupDetailsDto pickupDetails;
}
