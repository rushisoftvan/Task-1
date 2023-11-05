package com.softvan.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Positive;
import java.util.Objects;

public record PatientPageRequest(@Positive(message = "page number should be greater then zero") Integer pageNumber,
                                 @Positive(message = "page size should be greater then zero") Integer pageSize,
                                 String searchText, Boolean hasBloodPressure, Boolean hasEnergy) {

        public PatientPageRequest(@Positive(message = "page number should be greater then zero") Integer pageNumber, @Positive(message = "page size should be greater then zero") Integer pageSize, String searchText, Boolean hasBloodPressure, Boolean hasEnergy) {
                this.pageNumber = Objects.nonNull(pageNumber) ? pageNumber : 1;
                this.pageSize = Objects.nonNull(pageSize) ? pageSize : 5;
                this.searchText = searchText;
                this.hasBloodPressure = hasBloodPressure;
                this.hasEnergy = hasEnergy;
        }
}
