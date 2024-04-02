package com.meri.murdermysterygame.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DriversLicenseDto{
    private Long id;
    private int age;
    private int height;
    private String eyeColor;
    private String hairColor;
    private String gender;
    private String carModel;
}
