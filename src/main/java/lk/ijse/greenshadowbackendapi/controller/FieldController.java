package lk.ijse.greenshadowbackendapi.controller;

import lk.ijse.greenshadowbackendapi.dto.FieldStatus;
import lk.ijse.greenshadowbackendapi.dto.impl.CropDTO;
import lk.ijse.greenshadowbackendapi.dto.impl.FieldDTO;
import lk.ijse.greenshadowbackendapi.exception.DataPersistException;
import lk.ijse.greenshadowbackendapi.exception.FieldNotFoundException;
import lk.ijse.greenshadowbackendapi.service.CropService;
import lk.ijse.greenshadowbackendapi.service.FieldService;
import lk.ijse.greenshadowbackendapi.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/fields")
@CrossOrigin
public class FieldController {

    @Autowired
    FieldService fieldService;
    @Autowired
    CropService cropService;

    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveField(
            @RequestParam("fieldCode")String id,
            @RequestParam("fieldName") String fieldName,
            @RequestParam("fieldLocation") String fieldLocation,
            @RequestParam("fieldSize") Double fieldSize,
            @RequestParam("fieldImage01") MultipartFile fieldImage01,
            @RequestParam("fieldImage02") MultipartFile fieldImage02,
            @RequestParam("cropCode") String cropCode
    ) {
        String base64FieldImage01 = "";
        String base64FieldImage02 = "";
        try {
            byte[] bytesFieldImage01 = fieldImage01.getBytes();
            byte[] bytesFieldImage02 = fieldImage02.getBytes();
            base64FieldImage01 = AppUtil.fieldImage01ToBase64(bytesFieldImage01);
            base64FieldImage02 = AppUtil.fieldImage02ToBase64(bytesFieldImage02);

            var buildFieldDTO = new FieldDTO();
            buildFieldDTO.setFieldCode(id);
            buildFieldDTO.setFieldName(fieldName);
            buildFieldDTO.setFieldLocation(fieldLocation);
            buildFieldDTO.setFieldSize(fieldSize);
            buildFieldDTO.setFieldImage01(base64FieldImage01);
            buildFieldDTO.setFieldImage02(base64FieldImage02);
            buildFieldDTO.setCrop((CropDTO) cropService.getCrop(cropCode));
            fieldService.saveField(buildFieldDTO);
            System.out.println("Saved field : " + buildFieldDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Get all fields
    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    @GetMapping
    public List<FieldDTO> getAllFields() {
        return fieldService.getAllFields();
    }

    // Get a field by ID
    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    @GetMapping("/{id}")
    public FieldStatus getFieldById(@PathVariable String id) {
        return fieldService.getField(id);
    }

    // Update a field
    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    @PutMapping("/{fieldCode}")
    public ResponseEntity<FieldDTO> updateField(
            @PathVariable("fieldCode") String fieldCode,
            @RequestParam("fieldName") String fieldName,
            @RequestParam("fieldLocation") String fieldLocation,
            @RequestParam("fieldSize") Double fieldSize,
            @RequestParam("fieldImage01") MultipartFile fieldImage01,
            @RequestParam("fieldImage02") MultipartFile fieldImage02,
            @RequestParam("cropCode") String cropCode
    ) {

        String base64FieldImage01 = "";
        String base64FieldImage02 = "";
        try {
            byte[] bytesFieldImage01 = fieldImage01.getBytes();
            byte[] bytesFieldImage02 = fieldImage02.getBytes();
            base64FieldImage01 = AppUtil.fieldImage01ToBase64(bytesFieldImage01);
            base64FieldImage02 = AppUtil.fieldImage02ToBase64(bytesFieldImage02);

            var buildFieldDTO = new FieldDTO();
            buildFieldDTO.setFieldCode(fieldCode);
            buildFieldDTO.setFieldName(fieldName);
            buildFieldDTO.setFieldLocation(fieldLocation);
            buildFieldDTO.setFieldSize(fieldSize);
            buildFieldDTO.setFieldImage01(base64FieldImage01);
            buildFieldDTO.setFieldImage02(base64FieldImage02);
            buildFieldDTO.setCrop((CropDTO) cropService.getCrop(cropCode));
            fieldService.updateField(fieldCode,buildFieldDTO );
            return new ResponseEntity<>(buildFieldDTO, HttpStatus.OK);
        } catch (FieldNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete a field
    @PreAuthorize("hasRole('MANAGER') or hasRole('SCIENTIST')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteField(@PathVariable String id) {
        try {
            fieldService.deleteField(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (FieldNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
