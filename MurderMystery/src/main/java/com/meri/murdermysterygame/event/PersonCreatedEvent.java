package com.meri.murdermysterygame.event;

import lombok.*;

@Builder
public record PersonCreatedEvent (
    Long personId,
    String name,
    Integer addressNumber,
    String addressStreetName){
}
