package com.softvan.dto.response;

import com.softvan.dto.PatientDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PatientPagedResponse {

    private List<PatientDto > patientlist;

    private long totalRecords;

    private boolean isFirstPage;

    private boolean isLastPage;

    private boolean hasPrevious;

    private boolean hasNext;


}
