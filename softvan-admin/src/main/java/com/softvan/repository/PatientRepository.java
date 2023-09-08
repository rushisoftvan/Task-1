package com.softvan.repository;

import com.softvan.dto.PatientDto;
import com.softvan.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PatientRepository  extends JpaRepository<PatientEntity,Integer> {

    @Query("select p From PatientEntity p JOIN FETCH p.patientInfoEntity where p.id=:id")
    Optional<PatientEntity> getPatientEntityById(@Param("id") Integer id);

    //
     @Query("select new com.softvan.dto.PatientDto(p.firstName,p.lastName,p.email,p.dateOfBirth,p.createdDateTime,p.updatedDateTime,p.status,p.patientInfoEntity.hasAllergy,p.patientInfoEntity.hasBloodPressure) from PatientEntity p join  p.patientInfoEntity  where p.status='ACTIVE'")
    List<PatientDto> getAllPatient();
   // @Query(" select p From PatientEntity p JOIN FETCH p.patientInfoEntity where p.status='ACTIVE'")
    //List<PatientEntity> getAllPatient();// taking objects and putting them outside of the JPA context

}
