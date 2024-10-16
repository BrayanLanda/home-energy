package com.home.home_energy.reward;

import com.home.home_energy.consumption.Consumption;
import com.home.home_energy.consumption.ConsumptionRepository;
import com.home.home_energy.user.User;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RewardService{

    @Autowired
    private RewardRepository rewardRepository;


    public void assignRewardForLowConsumption(User user, Consumption previousConsumption,Consumption currentConsumption){
        // Calcular la diferencia de energía
        double energyDifference = previousConsumption.getEnergyUsed() - currentConsumption.getEnergyUsed();

        // Solo asignar recompensa si el consumo actual es menor que el anterior
        if (energyDifference > 0) {
            // Determinar el nivel de recompensa según la diferencia de energía
            RewardUser rewardLevel = determineRewardLevel(energyDifference);

            // Crear y guardar recompensa
            Reward reward = new Reward();
            reward.setDescription("Recompensa por Bajo consumo de Energía");
            reward.setThresholdEnergy(energyDifference);
            reward.setRewardPoints(calculateRewardPoints(rewardLevel));
            reward.setUser(user);
            reward.setRewardLevel(rewardLevel);
            rewardRepository.save(reward);
        }
    }

    private int calculateRewardPoints(RewardUser rewardLevel) {
        switch (rewardLevel){
            case HIGH:
                return 100;
            case MEDIUM:
                return 50;
            case LOW:
                return 25;
            default:
                return 0;
        }
    }

    //determinar el nivel de recompensa basado en la diferencia de consumo
    private RewardUser determineRewardLevel(double energyDifference) {
        if (energyDifference > 100){
            return RewardUser.HIGH;
        }else if (energyDifference >50){
            return RewardUser.MEDIUM;
        }else{
            return RewardUser.LOW;
        }
    }

    // Método para obtener las recompensas de un usuario por su ID
    public List<Reward> getRewardsByUserId(String userId) {
        return rewardRepository.findByUserIdUser(userId);
    }
}
