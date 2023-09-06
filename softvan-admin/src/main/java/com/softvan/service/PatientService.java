package com.softvan.service;

import com.softvan.Mapper.PatientMapper;
import com.softvan.dto.request.PatientAddRequest;
import com.softvan.dto.request.UpdatePatientDetailRequest;
import com.softvan.dto.response.PatientResponse;
import com.softvan.entity.PatientEntity;
import com.softvan.enums.StatusEnum;
import com.softvan.exception.CustomException;
import com.softvan.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientService {

    private final PatientMapper patientMapper;

    private final PatientRepository patientRepository;

    public PatientResponse savePatientDetails(PatientAddRequest patientAddRequest){

        log.info("<<<<<<<<< savePatientDetails()");
        checkAddPatientRequsteNull(patientAddRequest);
        PatientEntity patientEntity = this.patientMapper.toEntity(patientAddRequest);
        PatientEntity savedPatient = this.patientRepository.save(patientEntity);
        log.info("savePatientDetails() >>>>>>>");
        return  this.patientMapper.toResponseDto(savedPatient);
    }

    public PatientResponse fetchPatientById(Integer id){
        log.info("<<<<<<<<< fetchPatientById");
        PatientEntity dbPatient = getPatientEntity(id);
        log.info("fetchPatientById() >>>>>>>");
        return this.patientMapper.toResponseDto(dbPatient);
    }



    public String deletePatientById(Integer id){
        PatientEntity patientEntity = getPatientEntity(id);
        patientEntity.setStatus(StatusEnum.IN_ACTIVE);
        PatientEntity deletedPatient = this.patientRepository.save(patientEntity);
        return "Patient has been deleted";

    }

    public PatientResponse updatePatientDetails(Integer id, UpdatePatientDetailRequest updatePatientDetailRequest){
        if(updatePatientDetailRequest ==null){
            throw new  CustomException("update Request should not be null");
        }
        PatientEntity patientEntity = getPatientEntity(id);
        patientEntity.setFirstName(updatePatientDetailRequest.getFirstName());
        patientEntity.setLastName(updatePatientDetailRequest.getLastName());
        patientEntity.setEmail(updatePatientDetailRequest.getEmail());
        patientEntity.setDateOfBirth(updatePatientDetailRequest.getDateOfBirth());
        patientEntity.setStatus(updatePatientDetailRequest.getStaus());
        PatientEntity updatedPatient = this.patientRepository.save(patientEntity);
        return  this.patientMapper.toResponseDto(updatedPatient);
    }

    public List<PatientResponse> getPatientList(){
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setStatus(StatusEnum.ACTIVE);

        Example<PatientEntity> productEntityExample = Example.of(patientEntity);

        List<PatientEntity> patientEntities = this.patientRepository.findAll(productEntityExample);
       return this.patientMapper.toDtoList(patientEntities);
    }


    private static void checkAddPatientRequsteNull(PatientAddRequest patientAddRequest) {
        if(patientAddRequest ==null)
        {
            throw new CustomException("patientAddRequest should not be null");
        }
    }

    private PatientEntity getPatientEntity(Integer id) {
        PatientEntity dbPatient = this.patientRepository.findById(id).orElseThrow(() -> new CustomException("Patient is not available for this id"));
        return dbPatient;
    }
}