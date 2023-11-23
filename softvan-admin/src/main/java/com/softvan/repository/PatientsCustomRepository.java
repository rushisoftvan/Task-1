package com.softvan.repository;

import com.softvan.dto.PatientDto;
import com.softvan.dto.request.PatientPageRequest;
import org.springframework.data.domain.Page;


public interface PatientsCustomRepository {
        public Page<PatientDto> search(PatientPageRequest patientPageRequest);
}
