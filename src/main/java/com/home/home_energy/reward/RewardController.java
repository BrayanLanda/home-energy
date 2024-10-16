package com.home.home_energy.reward;

import com.home.home_energy.consumption.Consumption;
import com.home.home_energy.user.User;
import com.home.home_energy.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {

    @Autowired
    private RewardService rewardService;

    @Autowired
    private UserRepository userRepository;

    // Endpoint para obtener las recompensas de un usuario por su ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Reward>> getUserRewards(@PathVariable String userId) {
        // Verificar si el usuario existe
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // Obtener las recompensas del usuario
        List<Reward> userRewards = rewardService.getRewardsByUserId(userId);
        return ResponseEntity.ok(userRewards);
    }
}
