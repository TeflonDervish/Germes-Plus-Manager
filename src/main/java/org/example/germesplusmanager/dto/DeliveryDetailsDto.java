package org.example.germesplusmanager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class DeliveryDetailsDto {
    private String address;
    private String recipient;
    private String phone;
    private boolean needsLifting;
    private Integer floor;
    private String elevatorType;
}
