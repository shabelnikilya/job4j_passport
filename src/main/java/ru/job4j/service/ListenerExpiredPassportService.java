package ru.job4j.service;

import com.google.gson.Gson;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.job4j.model.dtomodel.DTOPassport;

@Component
public class ListenerExpiredPassportService {
    private final Gson gson;

    @Autowired
    public ListenerExpiredPassportService(Gson gson) {
        this.gson = gson;
    }

    @KafkaListener(topics = {"expired-passport"})
    public void sendEmail(ConsumerRecord<Integer, String> input) {
        DTOPassport passports = gson.fromJson(input.value(), DTOPassport.class);
        System.out.printf("Hello, %s!\n Your passport is expired. Change it!", passports.getName());
    }
}
