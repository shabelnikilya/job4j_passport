package ru.job4j.service;

import ru.job4j.model.Passport;

public interface Service {

    Passport save(Passport passport);

    Passport findById(int id);

    void update(int id, Passport passport);

    void remove(int id);

    Iterable<Passport> findAll();

    Iterable<Passport> findBySeries(String series);

    Iterable<Passport> findPassportsExpired();

    Iterable<Passport> findByPassPortSoonExpired(int month);
}
