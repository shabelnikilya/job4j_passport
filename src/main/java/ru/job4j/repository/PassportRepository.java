package ru.job4j.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.model.Passport;

import java.util.Date;

public interface PassportRepository extends CrudRepository<Passport, Integer> {

    Iterable<Passport> findBySeries(String series);

    @Query("from Passport p where p.expired > current_timestamp")
    Iterable<Passport> findByPassportEndAfterNow();

    @Query("from Passport p where current_timestamp between :param and p.expired")
    Iterable<Passport> findByPassportEndSoonExpired(@Param("param") Date month);
}
