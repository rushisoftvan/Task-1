package com.softvan.controller;

import com.softvan.dto.PatientDto;
import com.softvan.dto.request.PatientCreateRequest;
import com.softvan.dto.request.PatientPageRequest;
import com.softvan.dto.request.UpdatePatientDetailRequest;
import com.softvan.dto.response.ApiResponse;
import com.softvan.dto.response.PatientPagedResponse;
import com.softvan.dto.response.PatientResponse;
import com.softvan.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public ApiResponse addPatient(@RequestBody @Valid PatientCreateRequest patientAddRequest) {
        PatientResponse patientResponse = this.patientService.savePatientDetails(patientAddRequest);
        return new ApiResponse(patientResponse, HttpStatus.CREATED.value());
    }

    @GetMapping("/{id}")
    public ApiResponse getPatientById(@PathVariable("id") Integer patientId) {
        PatientResponse patientResponse = this.patientService.fetchPatientById(patientId);
        return new ApiResponse(patientResponse, HttpStatus.OK.value());
    }

    @DeleteMapping("/{id}")
    public ApiResponse deletePatientById(@PathVariable("id") Integer patientId) {
        String patientDeleteMessage = this.patientService.deletePatientById(patientId);
        return new ApiResponse(patientDeleteMessage, HttpStatus.OK.value());
    }

    @PutMapping("/{id}")
    public ApiResponse updatePatient(@PathVariable("id") Integer patientId, @RequestBody @Valid UpdatePatientDetailRequest updatePatientDetailRequest) {
        PatientResponse patientResponse = this.patientService.updatePatient(patientId, updatePatientDetailRequest);
        return new ApiResponse(patientResponse, HttpStatus.OK.value());
    }

    @PostMapping("/page")
    public ApiResponse getAllPatient(@Valid @RequestBody PatientPageRequest patientPageRequest) {
        PatientPagedResponse patientList = this.patientService.getPatientList(patientPageRequest);
        return new ApiResponse(patientList, HttpStatus.OK.value());
    }
}
