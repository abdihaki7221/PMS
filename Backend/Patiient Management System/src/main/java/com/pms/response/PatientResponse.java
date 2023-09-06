package com.pms.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author A_Omar
 * **/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientResponse {
  private  String statusCode;
  private String message;
  private  String identificationNumber;
}
