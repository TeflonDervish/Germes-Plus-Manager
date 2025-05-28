package org.example.germesplusmanager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class PickupDetailsDto {
    private Long pickupPointId;
    private LocalDate pickupDate;
}
