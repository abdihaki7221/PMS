package com.pms.repository;


import com.pms.model.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author A_Omar
 * **/

@Repository
public interface PatientsRepository extends JpaRepository<Patients,Long> {


  Patients findByIdentificationNumber(String identificationNumber);

}
