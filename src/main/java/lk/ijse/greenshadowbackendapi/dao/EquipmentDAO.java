package lk.ijse.greenshadowbackendapi.dao;

import lk.ijse.greenshadowbackendapi.entity.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentDAO extends JpaRepository<EquipmentEntity, String> {
}
