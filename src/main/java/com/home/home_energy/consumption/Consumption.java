package com.home.home_energy.consumption;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.home.home_energy.user.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "consumptions")
public class Consumption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConsumption;

    private int month;
    private int year;
    private double energyUsed;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "idUser")
    @JsonBackReference
    private User user;





}
