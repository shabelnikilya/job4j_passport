package ru.job4j.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.job4j.model.dtomodel.DTOPassport;

@Service
public class ListenerExpiredPassportService {

    @KafkaListener(topics = {"expired-passport"})
    public void sendEmail(DTOPassport input) {
        System.out.printf("Hello, %s!\n Your passport is expired. Change it!", input.getName());
    }
}
