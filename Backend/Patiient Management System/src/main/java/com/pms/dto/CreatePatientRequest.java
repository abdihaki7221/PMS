package com.pms.dto;

import com.pms.model.IdentificationType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author A_Omar
 * **/
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePatientRequest {

  private String firstName;
  private String middleName;
  private String lastName;
  private String identificationType;
  private String identificationNumber;
}
