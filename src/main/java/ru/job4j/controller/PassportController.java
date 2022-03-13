package ru.job4j.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.model.Passport;
import ru.job4j.service.Service;

import java.util.List;

@RestController
@RequestMapping("/passport")
public class PassportController {
    private final Service service;

    public PassportController(Service service) {
        this.service = service;
    }

    @PostMapping("/save")
    public Passport save(@RequestBody Passport passport) {
        return service.save(passport);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestParam int id, @RequestBody Passport passport) {
        HttpStatus result = HttpStatus.NOT_FOUND;
        if (service.findById(id) != null) {
            result = HttpStatus.OK;
            service.update(id, passport);
        }
        return new ResponseEntity<>(result);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam int id) {
        HttpStatus result = HttpStatus.NOT_FOUND;
        if (service.findById(id) != null) {
            result = HttpStatus.OK;
            service.remove(id);
        }
        return new ResponseEntity<>(result);
    }

    @GetMapping("/find")
    public List<Passport> findAll() {
        return (List<Passport>) service.findAll();
    }

    @GetMapping("/find-series")
    public List<Passport> findAllPassportsBySeries(@RequestParam String series) {
        return (List<Passport>) service.findBySeries(series);
    }

    @GetMapping("/unavaliabe")
    public List<Passport> findAllExpiredPassports() {
        return (List<Passport>) service.findPassportsExpired();
    }

    @GetMapping("/find-replaceable")
    public List<Passport> findReplaceable() {
        return (List<Passport>) service.findByPassPortSoonExpired(3);
    }
}
