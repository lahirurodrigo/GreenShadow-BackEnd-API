package lk.ijse.greenshadowbackendapi.dao;

import lk.ijse.greenshadowbackendapi.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleDAO extends JpaRepository<VehicleEntity, String> {
}
