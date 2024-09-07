package com.meri.murdermysterygame.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Table(name = "person")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "license_id", referencedColumnName = "id")
    @JsonBackReference
    private DriversLicense driversLicense;

    @Column(name = "address_number")
    private int addressNumber;

    @Column(name = "address_street_name")
    private String addressStreetName;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Interview> interviews;

    @Column(name = "fraud_check_id")
    private Long fraudCheckId;
}
