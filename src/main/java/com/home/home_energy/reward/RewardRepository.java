package com.home.home_energy.reward;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardRepository extends JpaRepository<Reward,Long> {
    // Método para obtener recompensas por ID de usuario
    List<Reward> findByUserIdUser(String idUser);
}
