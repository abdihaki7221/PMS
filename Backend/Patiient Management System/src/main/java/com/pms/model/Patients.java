package com.pms.model;

import jakarta.persistence.*;
import lombok.*;

/**
 *
 * @author A_Omar
 * **/

@Builder
@Entity
@Table(name = "tbl_patients")
@Data
@NoArgsConstructor
@NonNull
@AllArgsConstructor
public class Patients {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String firstName;
  private String middleName;
  private String lastName;

  @Enumerated(EnumType.STRING)
  private IdentificationType identificationType;
  private String identificationNumber;


}
