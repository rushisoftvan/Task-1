package com.softvan.repository;

import com.softvan.dto.PatientDto;
import com.softvan.entity.PatientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


public interface PatientRepository extends JpaRepository<PatientEntity, Integer>, PatientsCustomRepository {


    @Query("select p From PatientEntity p JOIN FETCH p.patientInfoEntity where p.id=:id")
    Optional<PatientEntity> fetchPatientWithPatientInfoEntityById(@Param("id") Integer id);

    //
    @Query("select new com.softvan.dto.PatientDto(p.id,p.firstName,p.lastName,p.email,p.dateOfBirth,p.createdDateTime,p.updatedDateTime,p.status,p.patientInfoEntity.hasAllergy,p.patientInfoEntity.hasBloodPressure) from PatientEntity p join  p.patientInfoEntity  where p.status='ACTIVE'")
    Page<PatientDto> getAllPatient(Pageable pageable);


    @Query("select new com.softvan.dto.PatientDto(p.id,p.firstName,p.lastName,p.email,p.dateOfBirth,p.createdDateTime,p.updatedDateTime,p.status,p.patientInfoEntity.hasAllergy,p.patientInfoEntity.hasBloodPressure) from PatientEntity p join  p.patientInfoEntity  where p.status='ACTIVE'")
    List<PatientDto> fetchAllPatient();// taking objects and putting them outside of the JPA context


}
