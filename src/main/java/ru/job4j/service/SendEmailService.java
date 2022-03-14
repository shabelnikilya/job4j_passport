package ru.job4j.service;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.job4j.model.Passport;
import ru.job4j.model.dtomodel.DTOPassport;
import ru.job4j.repository.PassportRepository;

@Component
public class SendEmailService {
    private final PassportRepository repository;
    private final Gson gson;
    private final KafkaTemplate<Integer, String> kafkaTemplate;

    @Autowired
    public SendEmailService(PassportRepository repository, Gson gson, KafkaTemplate<Integer, String> kafkaTemplate) {
        this.repository = repository;
        this.gson = gson;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedRate = 10000)
    public void scheduleTask() {
        Iterable<Passport> expiredPassports = repository.findByPassportEndAfterNow();
        for (Passport expiredPassport : expiredPassports) {
            kafkaTemplate.send("expired-passport",
                    gson.toJson(new DTOPassport(expiredPassport.getPeople().getName())));
        }
    }
}
