package com.demo.tallerdemo.repositories;

import com.demo.tallerdemo.models.pojo.ElectricVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface ElectricVehicleRepository extends JpaRepository<ElectricVehicle, Long> {

    Optional<ElectricVehicle> findVehicleByVin(String vin);

    List<ElectricVehicle> searchAllByCreatedDateBetween(Instant createdDate, Instant createdDate2);
}
