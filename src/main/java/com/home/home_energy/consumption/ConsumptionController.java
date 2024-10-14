package com.home.home_energy.consumption;

import com.home.home_energy.user.User;
import com.home.home_energy.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/consumption")
public class ConsumptionController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConsumptionService consumptionService;

    //Endpoint para registrar el consumo de un usuario
    @PostMapping("/add")
    public ResponseEntity<Consumption>addConsumption(@RequestBody ConsumptionRequest consumptionRequest){
        //buscar el id del usuario
        Optional<User>userOptional = userRepository.findById(consumptionRequest.getUserId().toString());
        if (!userOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        User user = userOptional.get();
        //crear el objeto consumption desde el dto

        Consumption consumption = new Consumption();
        consumption.setUser(user);

        consumption.setMonth(consumptionRequest.getMonth());
        consumption.setYear(consumptionRequest.getYear());
        consumption.setEnergyUsed(consumptionRequest.getEnergyUsed());

        //Guardar Consumo y retornar resultado
        Consumption savedConsumption = consumptionService.addConsumption(consumption);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedConsumption);
    }

    //obtener el historial de consumo de un usuario
    @GetMapping("/history/{userId}")
    public ResponseEntity<List<Consumption>>getUserConsumptionHistory(@PathVariable Long userId){
        //buscar el usuario por id
        Optional<User>userOptional = userRepository.findById(userId.toString());
        if (!userOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        User user = userOptional.get();
        List<Consumption>consumptionsHistory = consumptionService.getUserConsumptionHistory(user);
        return ResponseEntity.ok(consumptionsHistory);
    }


}
