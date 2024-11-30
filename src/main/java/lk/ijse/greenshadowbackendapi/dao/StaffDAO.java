package lk.ijse.greenshadowbackendapi.dao;

import lk.ijse.greenshadowbackendapi.entity.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffDAO extends JpaRepository<StaffEntity, String> {
}
