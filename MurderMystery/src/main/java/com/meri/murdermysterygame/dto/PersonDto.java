package com.meri.murdermysterygame.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PersonDto{
    private Long id;
    private String name;
    private Long licenseId;
    private DriversLicenseDto driversLicense;
    private int addressNumber;
    private String addressStreetName;
    private List<InterviewDto> interviewDtoList;
}
