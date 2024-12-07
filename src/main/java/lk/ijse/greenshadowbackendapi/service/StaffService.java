package lk.ijse.greenshadowbackendapi.service;

import lk.ijse.greenshadowbackendapi.dto.StaffStatus;
import lk.ijse.greenshadowbackendapi.dto.impl.StaffDTO;
import lk.ijse.greenshadowbackendapi.entity.StaffEntity;
import lk.ijse.greenshadowbackendapi.exception.DataPersistException;

import java.util.List;

public interface StaffService {
    void saveStaff(StaffDTO staffDTO) throws DataPersistException;

    List<StaffDTO> getAllStaffs();

    StaffStatus getStaff(String staffCode);

    void deleteStaff(String staffCode);

    void updateStaff(String staffCode, StaffDTO staffDTO);

    List<StaffDTO> getSortedStaffList();
}
