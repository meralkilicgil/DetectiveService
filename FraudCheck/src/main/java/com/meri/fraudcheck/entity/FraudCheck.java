package com.meri.fraudcheck.entity;

import com.meri.murdermysterygame.entity.Person;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FraudCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Boolean isFraudster;
    private LocalDateTime createdAt;
    private Long personId;
}
