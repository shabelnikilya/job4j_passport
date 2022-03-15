package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.job4j.model.Passport;
import ru.job4j.model.dtomodel.DTOPassport;
import ru.job4j.repository.PassportRepository;

@Service
public class SendEmailService {
    private final PassportRepository repository;
    private final KafkaTemplate<Integer, DTOPassport> kafkaTemplate;

    @Autowired
    public SendEmailService(PassportRepository repository, KafkaTemplate<Integer, DTOPassport> kafkaTemplate) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedRate = 10000)
    public void scheduleTask() {
        Iterable<Passport> expiredPassports = repository.findByPassportEndAfterNow();
        for (Passport expiredPassport : expiredPassports) {
            kafkaTemplate.send("expired-passport",
                                    new DTOPassport(expiredPassport.getPeople().getName()));
        }
    }
}
