package com.demo.tallerdemo.repositories;

import com.demo.tallerdemo.models.pojo.GasolineVehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface GasolineVehicleRepository extends JpaRepository<GasolineVehicle, Long> {

    Optional<GasolineVehicle> findVehicleByVin(String vin);

    List<GasolineVehicle> searchAllByCreatedDateBetween(Instant createdDate, Instant createdDate2);
}
