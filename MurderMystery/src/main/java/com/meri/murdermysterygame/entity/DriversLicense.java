package com.meri.murdermysterygame.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "drivers_license")
public class DriversLicense {

    @Id
    private Long id;
    private int age;
    private int height;
    private String eyeColor;
    private String hairColor;
    private String gender;
    private String carModel;

    @OneToOne(mappedBy = "driversLicense", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Person person;
}
