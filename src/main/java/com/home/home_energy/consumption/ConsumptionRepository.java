package com.home.home_energy.consumption;

import com.home.home_energy.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsumptionRepository extends JpaRepository<Consumption,Long>{

    //metodo para obetner el consumo de un usuario,ordenado por un año y mes decendente
    List<Consumption> findByUserOrderByYearDescMonthDesc(User user);

    //metodo para obtener el consumo de un usurario para un mes y un año determinado
    Optional<Consumption> findByUserAndMonthAndYear(User user, int month, int year );
}
