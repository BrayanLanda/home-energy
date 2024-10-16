package com.home.home_energy.address;

import com.home.home_energy.user.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCounter;

    private String country;
    private String city;

    private String street;

    @OneToOne(mappedBy = "address")
    private User user;
}
