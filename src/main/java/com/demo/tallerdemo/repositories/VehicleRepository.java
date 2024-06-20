package com.demo.tallerdemo.repositories;

import com.demo.tallerdemo.models.pojo.DieselVehicle;
import com.demo.tallerdemo.models.pojo.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> searchAllByCreatedDateBetween(Instant createdDate, Instant createdDate2);
}
