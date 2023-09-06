package com.softvan.controller;

import com.softvan.dto.request.PatientAddRequest;
import com.softvan.dto.request.UpdatePatientDetailRequest;
import com.softvan.dto.response.ApiResponse;
import com.softvan.dto.response.PatientResponse;
import com.softvan.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/pationts")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    
    @PostMapping
    public ApiResponse addPatient( @RequestBody @Valid  PatientAddRequest patientAddRequest){
        PatientResponse patientResponse = this.patientService.savePatientDetails(patientAddRequest);
        return  new ApiResponse(patientResponse, HttpStatus.CREATED.value());
    }

    @GetMapping("/{id}")
    public ApiResponse getPatientById(@PathVariable("id")  Integer patientId){
        PatientResponse patientResponse = this.patientService.fetchPatientById(patientId);
        return new ApiResponse(patientResponse,HttpStatus.OK.value());
    }

    @DeleteMapping("/{id}")
    public ApiResponse deletePatientById(@PathVariable("id") Integer patientId){
        String patientDeleteMessage = this.patientService.deletePatientById(patientId);
        return new ApiResponse(patientDeleteMessage,HttpStatus.OK.value());
    }

    @PutMapping("/{id}")
    public ApiResponse updatePatient( @PathVariable("id") @Positive(message ="PatientId should  be greater then zero") Integer patientId, @RequestBody   @Valid UpdatePatientDetailRequest updatePatientDetailRequest){
        PatientResponse patientResponse = this.patientService.updatePatientDetails(patientId, updatePatientDetailRequest);
        return new ApiResponse(patientResponse,HttpStatus.OK.value());
    }

    @GetMapping
    public ApiResponse getAllPatient(){
        List<PatientResponse> patientList = this.patientService.getPatientList();
        return new ApiResponse(patientList,HttpStatus.OK.value());
    }
}
