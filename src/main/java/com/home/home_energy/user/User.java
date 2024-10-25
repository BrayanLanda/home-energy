package com.home.home_energy.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.home.home_energy.address.Address;
import com.home.home_energy.consumption.Consumption;
import com.home.home_energy.reward.Reward;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idUser;

    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "address_id",referencedColumnName = "idCounter")
    private Address address;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Consumption> consumptions;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Reward> rewards;

    public  User(UserRequest userRequest){
        this.name = userRequest.getName();
        this.email = userRequest.getEmail();
        this.password = userRequest.getPassword();
        this.address = Address.builder()
                .country("COL")
                .city(userRequest.getCity())
                .street(userRequest.getStreet())
                .build();
    }
}
