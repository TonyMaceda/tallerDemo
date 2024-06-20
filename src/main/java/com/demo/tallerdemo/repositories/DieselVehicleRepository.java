package com.demo.tallerdemo.repositories;

import com.demo.tallerdemo.models.pojo.DieselVehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface DieselVehicleRepository extends JpaRepository<DieselVehicle, Long> {

    Optional<DieselVehicle> findVehicleByVin(String vin);

    List<DieselVehicle> searchAllByCreatedDateBetween(Instant createdDate, Instant createdDate2);
}
