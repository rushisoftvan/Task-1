package com.softvan.Mapper;

import com.softvan.dto.request.PatientAddRequest;
import com.softvan.dto.response.PatientResponse;
import com.softvan.entity.PatientEntity;
import com.softvan.entity.PatientInfoEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class PatientMapper {

    public PatientEntity toEntity(PatientAddRequest patientAddRequest){
        log.info("<<<<<<<<< toEntity() ");
        PatientEntity patientEntity = new PatientEntity();

        PatientInfoEntity patientInfoEntity = new PatientInfoEntity();
        patientInfoEntity.setHasAllergy(patientAddRequest.getHasAllergy());
        patientInfoEntity.setHasBloodPressure(patientAddRequest.getHasBloodPressure());

        patientEntity.setFirstName(patientAddRequest.getFirstName());
        patientEntity.setLastName(patientAddRequest.getLastName());
        patientEntity.setEmail(patientAddRequest.getEmail());
        patientEntity.setDateOfBirth(patientAddRequest.getDateOfBirth());
        patientEntity.setPatientInfoEntity(patientInfoEntity);
        log.info("toEntity() >>>>>>>>");
        return patientEntity;
    }

    public PatientAddRequest toDto(PatientEntity patientEntity){
        log.info("<<<<<<<<< toDto() ");
        PatientAddRequest patientAddRequest = new PatientAddRequest();
        patientAddRequest.setFirstName(patientAddRequest.getFirstName());
        patientAddRequest.setLastName(patientEntity.getLastName());
        patientAddRequest.setEmail(patientEntity.getEmail());
        patientAddRequest.setDateOfBirth(patientAddRequest.getDateOfBirth());
        log.info("toDto() >>>>>>>>");
        return patientAddRequest;
    }

     public PatientResponse toResponseDto(PatientEntity patientEntity){
         log.info("<<<<<<<<< toResponseDto() ");
         PatientResponse patientResponse = new PatientResponse();
         patientResponse.setFirstName(patientEntity.getFirstName());
         patientResponse.setLastName(patientEntity.getLastName());
         patientResponse.setEmail(patientEntity.getEmail());
         patientResponse.setCreatedOn(patientEntity.getCreatedDateTime());
         patientResponse.setUpdatedOn(patientEntity.getUpdatedDateTime());
         patientResponse.setDateOfBirth(patientEntity.getDateOfBirth());
         patientResponse.setStatusEnum(patientEntity.getStatus());
         log.info("toResponseDto() >>>>>>>>");
         return  patientResponse;
     }
    public List<PatientResponse> toDtoList(List<PatientEntity> patientEntities){
        List<PatientResponse> patientList = new ArrayList<>();

        for(PatientEntity patientEntity : patientEntities){
            PatientResponse patientResponse = toResponseDto(patientEntity);
            patientList.add(patientResponse);
        }
        return patientList;
    }


}
