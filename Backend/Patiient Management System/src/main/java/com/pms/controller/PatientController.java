package com.pms.controller;


import com.pms.dto.CreatePatientRequest;
import com.pms.model.FypModel;
import com.pms.response.PatientResponse;
import com.pms.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.pms.utils.Constants.CREATE;
import static com.pms.utils.Constants.UPDATE_PATIENT_ID;

/**
 *
 * @author A_Omar
 * **/
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class PatientController {


  private final PatientService service;
  @PostMapping(CREATE)
  public ResponseEntity<Map<String,Object>>createPatients(@RequestBody CreatePatientRequest request){
    return service.createPatient(request);
  }

  @PutMapping(UPDATE_PATIENT_ID)
  public ResponseEntity<Map<String,Object>> updatePatients(@PathVariable Long id,@RequestBody CreatePatientRequest request){
    return service.updatePatient(id,request);
  }

  @GetMapping("/get/list/{page}/{size}")
  public ResponseEntity<?> getRecords(@PathVariable String page, @PathVariable String size){
    return service.getRecord(page,size);

  }



}
