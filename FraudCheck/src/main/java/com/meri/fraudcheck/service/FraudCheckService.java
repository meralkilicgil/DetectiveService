package com.meri.fraudcheck.service;

import com.meri.fraudcheck.repository.FraudCheckRepository;
import com.meri.fraudcheck.dto.FraudCheckDto;
import com.meri.fraudcheck.entity.FraudCheck;
import com.meri.fraudcheck.message.MessageService;
import com.meri.murdermysterygame.event.PersonCreatedEvent;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FraudCheckService {

    private static final Logger log = LoggerFactory.getLogger(FraudCheckService.class);
    private final FraudCheckRepository fraudHistoryRepository;
    private final MessageService messageService;

    public boolean isFraudulentPerson(Long personId) {
        List<Optional<FraudCheck>> result = fraudHistoryRepository.findByPersonId(personId);
        FraudCheck fraudCheck = null;
        if (!result.isEmpty() && result.get(0).isPresent()){
            fraudCheck = result.get(0).get();
        }
        boolean fraudster = false;
        if(fraudCheck != null){
            fraudster = fraudCheck.getIsFraudster();
            log.info("Fraud check result for person with id: " + personId + " is: " + fraudster);
            FraudCheckDto fraudCheckDto = FraudCheckDto.builder()
                                                        .id(fraudCheck.getId())
                                                        .personId(fraudCheck.getPersonId())
                                                        .isFraudster(fraudCheck.getIsFraudster())
                                                        .createdAt(fraudCheck.getCreatedAt())
                                                        .build();
            //messageService.sendFraudCheckNotification("Fraud check has been processed for the person with id: " + personId + " and result is: " + fraudCheckDto);
        }
        return fraudster;
    }

    public void reportFraudulent(Long personId){
        FraudCheck fraudCheck = fraudHistoryRepository.save(
                FraudCheck.builder()
                        .isFraudster(true)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        messageService.sendFraudCheckNotification("Fraudulent person with id: " + personId + " has been reported. Fraud check id: " + fraudCheck.getId());
    }

    public void deleteFraudulent(Integer fraudId){
        fraudHistoryRepository.deleteById(fraudId);
    }

    public List<FraudCheckDto> getAllFrauds(){
        List<FraudCheck> frauds = fraudHistoryRepository.findAll();
        return frauds.stream().map(FraudCheckDto::fromEntity).toList();
    }

    public void deleteAllFrauds(){
        fraudHistoryRepository.deleteAll();
    }

    @KafkaListener(topics = "person-created", groupId = "fraud-check-group")
    public void handlePersonCreatedEvent(PersonCreatedEvent event){
        log.info("Person create event is received: " + event);
        fraudHistoryRepository.save(
                FraudCheck.builder()
                        .personId(event.personId())
                        .isFraudster(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
    }
}
