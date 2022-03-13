package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.model.Passport;
import ru.job4j.repository.PassportRepository;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class PassportService implements ru.job4j.service.Service {
    private final PassportRepository repository;

    public PassportService(PassportRepository repository) {
        this.repository = repository;
    }

    @Override
    public Passport save(Passport passport) {
        return repository.save(passport);
    }

    @Override
    public Passport findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void update(int id, Passport passport) {
        passport.setId(id);
        repository.save(passport);
    }

    @Override
    public void remove(int id) {
        repository.deleteById(id);
    }

    @Override
    public Iterable<Passport> findAll() {
        return repository.findAll();
    }

    @Override
    public Iterable<Passport> findBySeries(String series) {
        return repository.findBySeries(series);
    }

    @Override
    public Iterable<Passport> findPassportsExpired() {
        return repository.findByPassportEndAfterNow();
    }

    @Override
    public Iterable<Passport> findByPassPortSoonExpired(int month) {
        LocalDateTime time = LocalDateTime.now().minusMonths(month);
        return repository.findByPassportEndSoonExpired(Date.from(time.toInstant(ZoneOffset.UTC)));
    }
}
