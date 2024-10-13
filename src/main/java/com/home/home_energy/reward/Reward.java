package com.home.home_energy.reward;

import com.home.home_energy.user.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "rewards")
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReward;

    private String description;
    private double thresholdEnergy;
    private int rewardPoints;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "idUser")
    private User user;

    @Enumerated(EnumType.STRING)
    private RewardUser rewardLevel;

}
