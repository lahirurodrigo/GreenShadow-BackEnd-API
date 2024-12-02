package lk.ijse.greenshadowbackendapi.service.impl;


import lk.ijse.greenshadowbackendapi.dao.MonitoringLogDAO;
import lk.ijse.greenshadowbackendapi.dto.impl.MonitoringLogDTO;
import lk.ijse.greenshadowbackendapi.entity.MonitoringLogEntity;
import lk.ijse.greenshadowbackendapi.exception.DataPersistException;
import lk.ijse.greenshadowbackendapi.exception.MonitoringLogNotFoundException;
import lk.ijse.greenshadowbackendapi.service.MonitoringLogService;
import lk.ijse.greenshadowbackendapi.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MonitoringLogServiceImpl implements MonitoringLogService {
    @Autowired
    private MonitoringLogDAO monitoringLogDAO;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveMonitoringLog(MonitoringLogDTO monitoringLogDTO) throws DataPersistException {
        // Check if the logCode already exists
        if (monitoringLogDAO.existsById(monitoringLogDTO.getLogCode())) {
            throw new DataPersistException("Monitoring Log with the same logCode already exists!");
        }

        // Convert DTO to Entity
        MonitoringLogEntity monitoringLogEntity = mapping.toMonitoringLogEntity(monitoringLogDTO);

        // Save the monitoring log entity
        MonitoringLogEntity savedLog = monitoringLogDAO.save(monitoringLogEntity);
        if (savedLog == null) {
            throw new DataPersistException("Monitoring Log not saved!");
        }
    }

    @Override
    public List<MonitoringLogDTO> getAllMonitoringLogs() {
        List<MonitoringLogEntity> allLogs = monitoringLogDAO.findAll();
        return mapping.toMonitoringLogDTOList(allLogs);
    }

    @Override
    public MonitoringLogDTO getMonitoringLog(String logCode) {
        if (monitoringLogDAO.existsById(logCode)) {
            MonitoringLogEntity selectedLog = monitoringLogDAO.getReferenceById(logCode);
            return mapping.toMonitoringLogDTO(selectedLog);
        } else {
            throw new MonitoringLogNotFoundException("Monitoring Log with logCode " + logCode + " not found!");
        }
    }

    @Override
    public void deleteMonitoringLog(String logCode) {
        if (monitoringLogDAO.existsById(logCode)) {
            monitoringLogDAO.deleteById(logCode);
        } else {
            throw new MonitoringLogNotFoundException("Monitoring Log with logCode " + logCode + " not found!");
        }
    }

    @Override
    public void updateMonitoringLog(String logCode, MonitoringLogDTO monitoringLogDTO) {
        if (!monitoringLogDAO.existsById(logCode)) {
            throw new MonitoringLogNotFoundException("Monitoring Log with logCode " + logCode + " not found!");
        }

        // Find the existing log entity by ID
        Optional<MonitoringLogEntity> existedLog = monitoringLogDAO.findById(logCode);

        MonitoringLogEntity monitoringLogEntity = existedLog.get();

        // Update values from DTO to the existing entity
        monitoringLogEntity.setLogDate(monitoringLogDTO.getLogDate());
        monitoringLogEntity.setLogDetails(monitoringLogDTO.getLogDetails());
        monitoringLogEntity.setObservedImage(monitoringLogDTO.getObservedImage());
        monitoringLogEntity.setStaff(mapping.toStaffEntity(monitoringLogDTO.getStaff()));
        monitoringLogEntity.setFields(mapping.toFieldEntity(monitoringLogDTO.getFields()));
        monitoringLogEntity.setCrop(mapping.toCropEntity(monitoringLogDTO.getCrop()));
    }
}
