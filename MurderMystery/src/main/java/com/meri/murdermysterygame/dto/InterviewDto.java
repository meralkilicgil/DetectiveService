package com.meri.murdermysterygame.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class InterviewDto{
    private Long id;
    private PersonDto person;
    private Long personId;
    private String transcript;
}
