package com.pms.utils;


import org.springframework.stereotype.Component;

/**
 *
 * @author A_Omar
 * **/
@Component
public class Constants {
  public static final String FAILED_STATUS_CODE = "99";
  public static final String CREATE = "/create/patient";
  public static final String FIRST_NAME_IS_REQUIRED = "first name is required";
  public static final String MIDDLE_NAME_IS_REQUIRED = "middle name is required";
  public static final String IDENTIFICATION_NO_IS_REQUIRED = "Identification No is required";
  public static final String LAST_NAME_IS_REQUIRED = "last name is required";
  public static final String STATUS = "status";
  public static final String IDENTIFICATION_NUMBER = "identificationNumber";
  public static final String UPDATE_PATIENT_ID = "/update/patient/{id}";
  public static final String SUCCESSFULLY_UPDATED = "Successfully Updated";
  public static final String MESSAGE = "message";
  public static final String IDENTIFICATION_TYPE_IS_REQUIRED = "identification type is required";
  public static final String NATIONAL_ID = "national id";
  public static final String PASSPORT_NUMBER = "passport number";
  public static final String ALIEN_ID = "alien id";
  public static final String SERVICE_ID = "service id";
  public static final String INVALID_IDENTIFICATION_TYPE = "invalid identification Type";
  public static final String SUCCESS_CODE = "00";

  public static final String SUCCESSFULLY_CREATED_PATIENT = "Successfully created patient";

}
