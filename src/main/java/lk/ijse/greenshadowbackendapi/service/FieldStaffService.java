package lk.ijse.greenshadowbackendapi.service;

import lk.ijse.greenshadowbackendapi.exception.DataPersistException;

import java.util.List;

public interface FieldStaffService {
    // Add staff members to a field
    void addStaffToField(String fieldId, List<String> staffIds);

    // Remove staff member from a field
    void removeStaffFromField(String fieldId, String staffId) throws DataPersistException;

    // Get all staff members associated with a field
    List<String> getStaffForField(String fieldId);
}
