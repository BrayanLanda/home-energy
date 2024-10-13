package com.home.home_energy.consumption;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsumptionRequest {

    private Long userId;
    private int month;
    private int year;
    private double energyUsed;

}
