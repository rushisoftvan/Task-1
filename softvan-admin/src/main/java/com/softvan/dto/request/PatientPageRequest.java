package com.softvan.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class PatientPageRequest {

    @NotNull(message = "pageNumber should not be null")
    @Positive(message = "page number should be greter then zero")
    private Integer pageNumber = 1;

    @NotNull(message = "pageSize should not be null")
    @Positive(message = "page size should be greter then zero")
    private Integer pageSize = 5;
}
