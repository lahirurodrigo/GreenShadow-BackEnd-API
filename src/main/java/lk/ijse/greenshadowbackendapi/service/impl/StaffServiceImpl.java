package lk.ijse.greenshadowbackendapi.service.impl;

import lk.ijse.greenshadowbackendapi.customStatusCode.SelectedEntityErrorStatus;
import lk.ijse.greenshadowbackendapi.dao.StaffDAO;
import lk.ijse.greenshadowbackendapi.dto.StaffStatus;
import lk.ijse.greenshadowbackendapi.dto.impl.StaffDTO;
import lk.ijse.greenshadowbackendapi.entity.StaffEntity;
import lk.ijse.greenshadowbackendapi.exception.DataPersistException;
import lk.ijse.greenshadowbackendapi.exception.StaffNotFoundException;
import lk.ijse.greenshadowbackendapi.service.StaffService;
import lk.ijse.greenshadowbackendapi.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffDAO staffDAO;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveStaff(StaffDTO staffDTO) throws DataPersistException {
        // Check if the staffId already exists
        if (staffDAO.existsById(staffDTO.getStaffId())) {
            throw new DataPersistException("Staff with the same ID already exists!");
        }

        // Convert DTO to Entity
        StaffEntity staffEntity = mapping.toStaffEntity(staffDTO);

        // Save the staff entity
        StaffEntity savedStaff = staffDAO.save(staffEntity);
        if (savedStaff == null) {
            throw new DataPersistException("Staff not saved!");
        }
    }

    @Override
    public List<StaffDTO> getAllStaffs() {
        List<StaffEntity> allStaffs = staffDAO.findAll();
        return mapping.toStaffDTOList(allStaffs);
    }

    @Override
    public StaffStatus getStaff(String staffCode) {
        if(staffDAO.existsById(staffCode)){
            StaffEntity selectedStaff = staffDAO.getReferenceById(staffCode);
            return mapping.toStaffDTO(selectedStaff);
        } else {
            return new SelectedEntityErrorStatus(2, "Staff with code " + staffCode + "not found!");
        }
    }

    @Override
    public void deleteStaff(String staffCode) {
        if (staffDAO.existsById(staffCode)) {
            staffDAO.deleteById(staffCode);
        } else {
            throw new StaffNotFoundException("Staff with id " + staffCode + " not Found");
        }
    }

    @Override
    public void updateStaff(String staffCode, StaffDTO staffDTO) {

        if (!staffDAO.existsById(staffDTO.getStaffId())) {
            throw new StaffNotFoundException("Staff with ID " + staffCode + " not found");
        }

        // Find the existing staff entity by ID
        Optional<StaffEntity> existedStaff = staffDAO.findById(staffCode);

            StaffEntity staffEntity = existedStaff.get();

            // Set the updated values from StaffDTO to the existing StaffEntity
            staffEntity.setFirstName(staffDTO.getFirstName());
            staffEntity.setLastName(staffDTO.getLastName());
            staffEntity.setDesignation(staffDTO.getDesignation());
            staffEntity.setGender(staffDTO.getGender());
            staffEntity.setJoinDate(staffDTO.getJoinDate());
            staffEntity.setDOB(staffDTO.getDOB());
            staffEntity.setAddress01(staffDTO.getAddress01());
            staffEntity.setAddress02(staffDTO.getAddress02());
            staffEntity.setAddress03(staffDTO.getAddress03());
            staffEntity.setAddress04(staffDTO.getAddress04());
            staffEntity.setAddress05(staffDTO.getAddress05());
            staffEntity.setContactNo(staffDTO.getContactNo());
            staffEntity.setEmail(staffDTO.getEmail());
            staffEntity.setRole(staffDTO.getRole());
    }

    @Override
    public List<StaffDTO> getSortedStaffList() {
        List<StaffEntity> staffList = staffDAO.findAll();
        List<StaffDTO> staffDTOList = mapping.toStaffDTOList(staffList); // Convert List<StaffEntity> to List<StaffDTO>


        // Sorting by first name only
        List<StaffDTO> sortedList = staffDTOList.stream()
                .sorted(Comparator.comparing(StaffDTO::getFirstName))
                .collect(Collectors.toList());

        return sortedList;  // Return the sorted list
    }
}
