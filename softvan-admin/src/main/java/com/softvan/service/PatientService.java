package com.softvan.service;

import com.softvan.Mapper.PatientMapper;
import com.softvan.dto.PatientDto;
import com.softvan.dto.request.PatientCreateRequest;
import com.softvan.dto.request.PatientPageRequest;
import com.softvan.dto.request.UpdatePatientDetailRequest;
import com.softvan.dto.response.PatientPagedResponse;
import com.softvan.dto.response.PatientResponse;
import com.softvan.entity.PatientEntity;
import com.softvan.enums.StatusEnum;
import com.softvan.exception.CustomException;
import com.softvan.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientService {

    public static final String PATIENT_HAS_BEEN_DELETED = "Patient has been deleted";
    private final PatientMapper patientMapper;

    private final PatientRepository patientRepository;

    public PatientResponse savePatientDetails(PatientCreateRequest patientAddRequest){

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



   @Transactional
    public String deletePatientById(Integer id){
        log.info("<<<<<<<<< deletePatientById()");
        PatientEntity patientEntity = getPatientEntity(id);
        patientEntity.setStatus(StatusEnum.IN_ACTIVE);
        PatientEntity deletedPatient = this.patientRepository.save(patientEntity);
        log.info("deletePatientById() >>>>>>>");
        return PATIENT_HAS_BEEN_DELETED;

    }

    @Transactional
    public PatientResponse updatePatient(Integer id, UpdatePatientDetailRequest updatePatientDetailRequest){
        log.info("<<<<<<<<< updatePatient()");
        if(updatePatientDetailRequest ==null){
            throw new  CustomException("update Request should not be null");
        }
        PatientEntity patientEntity = getPatientEntity(id);

        patientEntity.getPatientInfoEntity().setHasAllergy(updatePatientDetailRequest.getHasAllergy());
        patientEntity.getPatientInfoEntity().setHasBloodPressure(updatePatientDetailRequest.getHasBloodPressure());
        patientEntity.setFirstName(updatePatientDetailRequest.getFirstName());
        patientEntity.setLastName(updatePatientDetailRequest.getLastName());
        patientEntity.setEmail(updatePatientDetailRequest.getEmail());
        patientEntity.setDateOfBirth(updatePatientDetailRequest.getDateOfBirth());
        patientEntity.setStatus(updatePatientDetailRequest.getStatus());
        PatientEntity updatedPatient = this.patientRepository.save(patientEntity);
        log.info("updatePatient() >>>>>>>");
        return  this.patientMapper.toResponseDto(updatedPatient);
    }

    public PatientPagedResponse getPatientList(PatientPageRequest patientPageRequest){
        log.info("<<<<<<<<< getPatientList()");
        Integer pageNo=patientPageRequest.pageNumber()-1;
        Integer pageSize = patientPageRequest.pageSize();
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setStatus(StatusEnum.ACTIVE);

       //xample<PatientEntity> productEntityExample = Example.of(patientEntity);

        //List<PatientEntity> patientEntities = this.patientRepository.findAll(productEntityExample);
       // List<PatientEntity> allPatient = this.patientRepository.getAllPatient();
        //return this.patientMapper.toDtoList(allPatient);

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        int pageNumber = pageable.getPageNumber();
        log.info("getPatientList()  >>>>>>>");
        Page<PatientDto> patientPage = this.patientRepository.getAllPatient(pageable);
        return preparePatientPagedResponse(patientPage,pageNo);

    }

    private static PatientPagedResponse preparePatientPagedResponse(Page<PatientDto> patientPage,int pageNo) {
        List<PatientDto> patientList = patientPage.getContent();
        PatientPagedResponse patientPagedResponse = new PatientPagedResponse();
        patientPagedResponse.setPatientlist(patientList);
        patientPagedResponse.setTotalRecords(patientPage.getTotalElements());
        patientPagedResponse.setFirstPage(patientPage.isFirst());
        patientPagedResponse.setLastPage(patientPage.isLast());
        patientPagedResponse.setHasPrevious(patientPage.hasPrevious());
        patientPagedResponse.setHasNext(patientPage.hasNext());
        patientPagedResponse.setTotalPage(patientPage.getTotalPages());
        patientPagedResponse.setPageNo(pageNo);

       return  patientPagedResponse;
    }


    private static void checkAddPatientRequsteNull(PatientCreateRequest patientAddRequest) {
        if(patientAddRequest ==null)
        {
            throw new CustomException("patientAddRequest should not be null");
        }
    }

    private PatientEntity getPatientEntity(Integer id) {
        PatientEntity dbPatient = this.patientRepository.fetchPatientWithPatientInfoEntityById(id).orElseThrow(() -> new CustomException("Patient is not available for this id"));
        return dbPatient;
    }

    public List<PatientDto> fetchAllPatient(){
        return  this.patientRepository.fetchAllPatient();
    }

    public Page<PatientDto> searchPatient(PatientPageRequest patientPageRequest){
        Page<PatientDto> pagedPatientDto = this.patientRepository.search(patientPageRequest);
        return pagedPatientDto;
    }


}
