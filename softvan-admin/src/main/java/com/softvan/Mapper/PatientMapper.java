package com.softvan.Mapper;

import com.softvan.dto.request.PatientCreateRequest;
import com.softvan.dto.response.PatientResponse;
import com.softvan.entity.PatientEntity;
import com.softvan.entity.PatientInfoEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PatientMapper {

    public PatientEntity toEntity(PatientCreateRequest patientAddRequest){
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
         PatientInfoEntity patientInfoEntity = patientEntity.getPatientInfoEntity();
         patientResponse.setHasBloodPressure(patientInfoEntity.getHasBloodPressure());
         patientResponse.setHasAllergy(patientInfoEntity.getHasAllergy());
         // patientResponse.setHasAllergy(patientEntity.getPatientInfoEntity().getHasAllergy());
        // patientResponse.setHasBloodPressure(patientEntity.getPatientInfoEntity().getHasBloodPressure());
         log.info("toResponseDto() >>>>>>>>");
         return  patientResponse;
     }
//    public List<PatientResponse> toDtoList(List<PatientEntity> patientEntities){
//        List<PatientResponse> patientList = new ArrayList<>();
//
//        for(PatientEntity patientEntity : patientEntities){
//            PatientResponse patientResponse = toResponseDto(patientEntity);
//            patientList.add(patientResponse);
//        }
//        return patientList;



}
