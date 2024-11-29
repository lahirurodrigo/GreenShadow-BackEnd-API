package lk.ijse.greenshadowbackendapi.dao;

import lk.ijse.greenshadowbackendapi.entity.CropEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CropDAO extends JpaRepository<CropEntity, String> {
}
