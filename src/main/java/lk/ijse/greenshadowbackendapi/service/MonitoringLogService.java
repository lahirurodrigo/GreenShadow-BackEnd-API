package lk.ijse.greenshadowbackendapi.service;

import lk.ijse.greenshadowbackendapi.dto.impl.MonitoringLogDTO;
import lk.ijse.greenshadowbackendapi.exception.DataPersistException;

import java.util.List;

public interface MonitoringLogService {
    void saveMonitoringLog(MonitoringLogDTO monitoringLogDTO) throws DataPersistException;

    List<MonitoringLogDTO> getAllMonitoringLogs();

    MonitoringLogDTO getMonitoringLog(String logCode);

    void deleteMonitoringLog(String logCode);

    void updateMonitoringLog(String logCode, MonitoringLogDTO monitoringLogDTO);
}
