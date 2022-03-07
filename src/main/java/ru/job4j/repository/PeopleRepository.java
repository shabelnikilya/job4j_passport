package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.model.People;

public interface PeopleRepository extends CrudRepository<People, Integer> {

}
