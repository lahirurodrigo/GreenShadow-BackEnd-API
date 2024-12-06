package lk.ijse.greenshadowbackendapi.dao;

import lk.ijse.greenshadowbackendapi.entity.CropEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CropDAO extends JpaRepository<CropEntity, String> {
    @Query(value = "SELECT * FROM crop WHERE crop_category = :category", nativeQuery = true)
    List<CropEntity> findByCategoryNative(String category);
}
