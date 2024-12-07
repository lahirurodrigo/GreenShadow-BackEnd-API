package lk.ijse.greenshadowbackendapi.service.impl;

import lk.ijse.greenshadowbackendapi.dao.FieldDAO;
import lk.ijse.greenshadowbackendapi.dao.StaffDAO;
import lk.ijse.greenshadowbackendapi.dto.impl.FieldStaffDTO;
import lk.ijse.greenshadowbackendapi.entity.FieldEntity;
import lk.ijse.greenshadowbackendapi.entity.StaffEntity;
import lk.ijse.greenshadowbackendapi.exception.DataPersistException;
import lk.ijse.greenshadowbackendapi.exception.FieldNotFoundException;
import lk.ijse.greenshadowbackendapi.exception.StaffNotFoundException;
import lk.ijse.greenshadowbackendapi.service.FieldStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FieldStaffServiceImpl implements FieldStaffService {

    @Autowired
    private FieldDAO fieldDAO;

    @Autowired
    private StaffDAO staffDAO;

    // Add staff members to a field
    @Override
    public void addStaffToField(String fieldId, List<String> staffIds) {
        // Get the field entity
        FieldEntity field = fieldDAO.findById(fieldId).orElse(null);
        if (field == null) {
            throw new FieldNotFoundException("Field with id " + fieldId + " not Found");
        }

        // Get the staff entities
        List<StaffEntity> staffList = staffDAO.findAllById(staffIds);
        if (staffList.isEmpty()) {
            throw new StaffNotFoundException("Staff with id " + staffIds + " not Found");
        }

        // Add staff members to the field
        field.getStaffMembers().addAll(staffList);
        // Save the updated field entity
        fieldDAO.save(field);
    }

    // Remove staff member from a field
    @Override
    public void removeStaffFromField(String fieldId, String staffId) throws DataPersistException {
        // Get the field entity
        FieldEntity field = fieldDAO.findById(fieldId).orElse(null);
        if (field == null) {
            throw new FieldNotFoundException("Field with id " + fieldId + " not Found");
        }

        // Find the staff member in the field
        StaffEntity staff = staffDAO.findById(staffId).orElse(null);
        if (staff == null) {
            throw new StaffNotFoundException("Staff with id " + staffId + " not Found");
        }

        // Remove the staff member if associated
        boolean removed = field.getStaffMembers().remove(staff);
        if (removed) {
            // Save the updated field entity
            fieldDAO.save(field);
        } else {
            throw new DataPersistException();
        }
    }

    // Get all staff members associated with a field
    @Override
    public List<String> getStaffForField(String fieldId) {
        // Get the field entity
        FieldEntity field = fieldDAO.findById(fieldId).orElse(null);
        if (field == null) {
            System.out.println("Field not found: " + fieldId);
            return new ArrayList<>();
        }

        // Return names of associated staff
        return field.getStaffMembers()
                .stream()
                .map(staff -> staff.getStaffId())
                .collect(Collectors.toList());
    }
}
