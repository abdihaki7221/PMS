package com.pms.service;

import com.pms.dto.CreatePatientRequest;
import com.pms.model.FypModel;
import com.pms.model.IdentificationType;
import com.pms.model.Patients;
import com.pms.repository.FypRepository;
import com.pms.repository.PatientsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.pms.utils.Constants.*;


/**
 *
 * @author A_Omar
 * **/

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientService {


  private final PatientsRepository patientsRepository;
  private final FypRepository fypRepository;
  Map<String, Object> response = new HashMap<>();

  public ResponseEntity<Map<String, Object>> createPatient(CreatePatientRequest request) {


    String firstName = request.getFirstName();
    String middleName = request.getMiddleName();
    String idNumber = request.getIdentificationNumber();
    String idType = request.getIdentificationType();
    String lastName = request.getLastName();

    if (validateInputs(request, response)) {
      if (isValidIdentificationType(idType)) {

        Patients findPatient = patientsRepository.findByIdentificationNumber(idNumber);


        if (findPatient==null){
          Patients patients = new Patients();
          patients.setFirstName(firstName);
          patients.setMiddleName(middleName);
          patients.setLastName(lastName);
          patients.setIdentificationNumber(idNumber);
          patients.setIdentificationType(IdentificationType.valueOf(idType));

          Patients savedPatient = patientsRepository.save(patients);

          response.put(STATUS, SUCCESS_CODE);
          response.put(IDENTIFICATION_NUMBER, savedPatient.getIdentificationNumber());
          response.put(MESSAGE, SUCCESSFULLY_CREATED_PATIENT);
          response.put("id",savedPatient.getId());
          return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(response);
        }else {
          response.put(STATUS, FAILED_STATUS_CODE);
          response.put(MESSAGE, "Patient with Id no " + idNumber + " Already exists");
          return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(response);
        }

      } else {
        response.put(STATUS, FAILED_STATUS_CODE);
        response.put(MESSAGE, INVALID_IDENTIFICATION_TYPE);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(response);
      }
    } else {
      return ResponseEntity.status(HttpStatus.OK).body(response);
    }

      }
  public ResponseEntity<Map<String, Object>> updatePatient(Long id, CreatePatientRequest request) {

    String idType = request.getIdentificationType();

    if (validateInputs(request, response)) {
      log.info("response is {}" ,response);
      if (isValidIdentificationType(idType)) {
        Optional<Patients> patients = patientsRepository.findById(id);
        if (patients.isPresent()){
          Patients patientToUpdate = patients.get();
          patientToUpdate.setFirstName(request.getFirstName());
          patientToUpdate.setMiddleName(request.getMiddleName());
          patientToUpdate.setLastName(request.getLastName());
          patientToUpdate.setIdentificationNumber(request.getIdentificationNumber());
          patientToUpdate.setIdentificationType(IdentificationType.valueOf(request.getIdentificationType()));

          log.info("updated patient is {}",patientToUpdate);
          patientsRepository.save(patientToUpdate);
          response.put(STATUS, SUCCESS_CODE);
          response.put(MESSAGE, SUCCESSFULLY_UPDATED);
          response.put(IDENTIFICATION_NUMBER, patientToUpdate.getIdentificationNumber());
          response.put("id",patientToUpdate.getId());
          return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(response);
        }else {
          response.remove(IDENTIFICATION_NUMBER);
          response.remove("id");
          response.put(STATUS, FAILED_STATUS_CODE);
          response.put(MESSAGE, "No user with the id " + id + " exists");
          return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(response);
        }

      }else {
        response.put(STATUS, FAILED_STATUS_CODE);
        response.put(MESSAGE, INVALID_IDENTIFICATION_TYPE);
        return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(response);
      }

    }else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

  }


  private boolean isValidIdentificationType(String idType) {
    try {
      IdentificationType type = IdentificationType.valueOf(idType);
      return true;
    } catch (IllegalArgumentException e) {
      return false;
    }
  }

  private boolean validateInputs(CreatePatientRequest request, Map<String, Object> response) {
    String firstName = request.getFirstName();
    String middleName = request.getMiddleName();
    String idNumber = request.getIdentificationNumber();
    String idType = request.getIdentificationType();
    String lastName = request.getLastName();

    if (firstName.isEmpty() || firstName.isBlank()) {
      return setValidationFailureResponse(response, FIRST_NAME_IS_REQUIRED);
    } else if (middleName.isEmpty() || middleName.isBlank()) {
      return setValidationFailureResponse(response, MIDDLE_NAME_IS_REQUIRED);
    } else if (idNumber.isEmpty() || idNumber.isBlank()) {
      return setValidationFailureResponse(response, IDENTIFICATION_NO_IS_REQUIRED);
    } else if (lastName.isEmpty() || lastName.isBlank()) {
      return setValidationFailureResponse(response, LAST_NAME_IS_REQUIRED);
    } else if (idType.isEmpty() || idType.isBlank()) {
      return setValidationFailureResponse(response, IDENTIFICATION_TYPE_IS_REQUIRED);
    }
    return true;
  }

  private boolean setValidationFailureResponse(Map<String, Object> response, String message) {
    response.put(STATUS, com.pms.utils.Constants.FAILED_STATUS_CODE);
    response.put(MESSAGE, message);
    return false;
  }

  public ResponseEntity<?> getRecord(String page, String size) {
    Map<String,String> errorResponse = new HashMap<>();
    List<String> list = new ArrayList<>();


    try {
      int pageNo = Integer.parseInt(String.valueOf(page));
      int pageSize = Integer.parseInt(String.valueOf(size));

      if (pageNo>0 && pageSize>0 && pageSize<101){
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<FypModel> resultPage = fypRepository.findAll(pageable);

        List<FypModel> records = resultPage.getContent();
        return ResponseEntity.ok(records);


      }else {
        errorResponse.put("status","99");
        errorResponse.put("message","invalid page and size .");


        return ResponseEntity.badRequest().body(errorResponse);
      }

    } catch (NumberFormatException e) {
      // Handle invalid page or size values here, e.g., return a 400 Bad Request response.
      return ResponseEntity.badRequest().build();
    }

  }
}
