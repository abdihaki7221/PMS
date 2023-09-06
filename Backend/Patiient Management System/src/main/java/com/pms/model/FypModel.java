package com.pms.model;

import jakarta.persistence.*;
import lombok.*;

/**
 *
 * @author A_Omar
 * **/


@Builder
@Entity
@Table(name = "tbl_fyp")
@Data
@NoArgsConstructor
@NonNull
@AllArgsConstructor
public class FypModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Integer Year;
  private String Industry_aggregation_NZSIOC;
  private String Industry_code_NZSIOC;
  private String Industry_name_NZSIOC;
  private String Units;
  private String Variable_code;
  private String	Variable_name;
  private String Variable_category;
  private String Value;
  private String Industry_code_ANZSIC06;

}
