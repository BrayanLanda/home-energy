package com.home.home_energy.consumption;

import com.home.home_energy.reward.RewardService;
import com.home.home_energy.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConsumptionService {

    @Autowired
    private ConsumptionRepository consumptionRepository;
    @Autowired
    private RewardService rewardService;

    //metodo para registrar el consumo mensual del usuario
    public Consumption addConsumption(Consumption consumption){
        User user = consumption.getUser();

        //verificar si ya existe un consumo registrado para el mes y año dadoo
        Optional<Consumption>existingConsumption = consumptionRepository.findByUserAndMonthAndYear(
                user,consumption.getMonth(),consumption.getYear()
        );
        if (existingConsumption.isPresent()){
            throw new IllegalArgumentException("Ya existe un Registro de Consumo para este Mes y Año");
        }
        //guardar el nuevo consumo en la base de datos
        Consumption newConsumption = consumptionRepository.save(consumption);

        //verificar si el consumo ha desminuido y asignar recompensas si es necesario
        checkForReward(user,newConsumption);
        return newConsumption;

    }

    //verificar si el consumo disminuyo a comparacion del mes anterior
    private void checkForReward(User user, Consumption currentConsumption) {
        //obtenr consumos anteriores del usuario
        List<Consumption>previousConsumption = consumptionRepository.findByUserOrderByYearDescMonthDesc(user);

        if (previousConsumption.size()<1){
            //si no hay consumo anteior no se puede comparar
            return;
        }
        Consumption lastMonthConsumption = previousConsumption.get(0);

        //comparar el consumo actual con el mes anterior
        if (currentConsumption.getEnergyUsed()<lastMonthConsumption.getEnergyUsed()){
            rewardService.assignRewardForLowConsumption(user,lastMonthConsumption,currentConsumption);
        }
    }

    //obtener el historial completo de usuario
    public List<Consumption>getUserConsumptionHistory(User user){
        return consumptionRepository.findByUserOrderByYearDescMonthDesc(user);
    }
}
