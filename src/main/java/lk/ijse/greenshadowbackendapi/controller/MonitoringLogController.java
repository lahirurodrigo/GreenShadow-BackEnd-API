package lk.ijse.greenshadowbackendapi.controller;

import lk.ijse.greenshadowbackendapi.dto.MonitoringLogStatus;
import lk.ijse.greenshadowbackendapi.dto.impl.CropDTO;
import lk.ijse.greenshadowbackendapi.dto.impl.FieldDTO;
import lk.ijse.greenshadowbackendapi.dto.impl.MonitoringLogDTO;
import lk.ijse.greenshadowbackendapi.dto.impl.StaffDTO;
import lk.ijse.greenshadowbackendapi.exception.DataPersistException;
import lk.ijse.greenshadowbackendapi.exception.MonitoringLogNotFoundException;
import lk.ijse.greenshadowbackendapi.service.CropService;
import lk.ijse.greenshadowbackendapi.service.FieldService;
import lk.ijse.greenshadowbackendapi.service.MonitoringLogService;
import lk.ijse.greenshadowbackendapi.service.StaffService;
import lk.ijse.greenshadowbackendapi.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/monitoring-logs")
@CrossOrigin
public class MonitoringLogController {

    @Autowired
    MonitoringLogService monitoringLogService;

    @Autowired
    FieldService fieldService;

    @Autowired
    StaffService staffService;

    @Autowired
    CropService cropService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveMonitoringLog(
            @RequestParam("logCode") String logCode,
            @RequestParam("logDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date logDate,
            @RequestParam("logDetails") String logDetails,
            @RequestParam("observedImage") MultipartFile observedImage,
            @RequestParam("staffId") String staffId,
            @RequestParam("fieldCode") String fieldCode,
            @RequestParam("cropCode") String cropCode
    ) {
        try {
            byte[] imageBytes = observedImage.getBytes();
            String base64Image = AppUtil.monitoringLogImageToBase64(imageBytes);

            var monitoringLogDTO = new MonitoringLogDTO();
            monitoringLogDTO.setLogCode(logCode);
            monitoringLogDTO.setLogDate(logDate);
            monitoringLogDTO.setLogDetails(logDetails);
            monitoringLogDTO.setObservedImage(base64Image);
            monitoringLogDTO.setStaff((StaffDTO) staffService.getStaff(staffId));
            monitoringLogDTO.setFields((FieldDTO) fieldService.getField(fieldCode));
            monitoringLogDTO.setCrop((CropDTO) cropService.getCrop(cropCode));

            monitoringLogService.saveMonitoringLog(monitoringLogDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all monitoring logs
    @GetMapping
    public List<MonitoringLogDTO> getAllMonitoringLogs() {
        return monitoringLogService.getAllMonitoringLogs();
    }

    // Get a monitoring log by ID
    @GetMapping("/{logCode}")
    public MonitoringLogStatus getMonitoringLogById(@PathVariable String logCode) {
        return monitoringLogService.getMonitoringLog(logCode);
    }

    // Update a monitoring log
    @PutMapping("/{logCode}")
    public ResponseEntity<MonitoringLogDTO> updateMonitoringLog(
            @PathVariable("logCode") String logCode,
            @RequestParam("logDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date logDate,
            @RequestParam("logDetails") String logDetails,
            @RequestParam("observedImage") MultipartFile observedImage,
            @RequestParam("staffId") String staffId,
            @RequestParam("fieldCode") String fieldCode,
            @RequestParam("cropCode") String cropCode
    ) {
        try {
            byte[] imageBytes = observedImage.getBytes();
            String base64Image = AppUtil.cropImageToBase64(imageBytes);

            var monitoringLogDTO = new MonitoringLogDTO();
            monitoringLogDTO.setLogCode(logCode);
            monitoringLogDTO.setLogDate(logDate);
            monitoringLogDTO.setLogDetails(logDetails);
            monitoringLogDTO.setObservedImage(base64Image);
            monitoringLogDTO.setStaff((StaffDTO) staffService.getStaff(staffId));
            monitoringLogDTO.setFields((FieldDTO) fieldService.getField(fieldCode));
            monitoringLogDTO.setCrop((CropDTO) cropService.getCrop(cropCode));

            monitoringLogService.updateMonitoringLog(logCode, monitoringLogDTO);
            return new ResponseEntity<>(monitoringLogDTO, HttpStatus.OK);
        } catch (MonitoringLogNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete a monitoring log
    @DeleteMapping("/{logCode}")
    public ResponseEntity<Void> deleteMonitoringLog(@PathVariable String logCode) {
        try {
            monitoringLogService.deleteMonitoringLog(logCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (MonitoringLogNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
