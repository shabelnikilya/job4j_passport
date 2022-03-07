package ru.job4j.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.job4j.model.Passport;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/passport")
public class PassportAPI {

    @Value("${api-url}")
    private String url;
    private final RestTemplate client;

    @Autowired
    public PassportAPI(RestTemplate client) {
        this.client = client;
    }

    @PostMapping("/save")
    public Passport save(Passport passport) {
        return client.postForEntity(
                url, passport, Passport.class
        ).getBody();
    }

    @PutMapping("update")
    public void update(int id, Passport passport) {
        client.exchange(
                String.format("%s?id=%s", url, id),
                HttpMethod.PUT,
                new HttpEntity<>(passport),
                Void.class
        );
    }

    @DeleteMapping("/delete")
    public void remove(int id) {
        client.exchange(
                String.format("%s?id=%s", url, id),
                HttpMethod.DELETE,
                HttpEntity.EMPTY,
                Void.class
        );
    }

    @GetMapping("/find")
    public List<Passport> findAll() {
        return getList(String.format(
                "%s/find", url
        ));
    }

    @GetMapping("/find-series")
    public List<Passport> findBySeries(String series) {
        return getList(String.format(
                "%s/find-series?series=%s", url, series
        ));
    }

    @GetMapping("/unavaliabe")
    public List<Passport> findPassportsExpired() {
        return getList(String.format(
                "%s/unavaliabe", url
        ));
    }

    @GetMapping("/find-replaceable")
    public List<Passport> findPassportsSoonExpired() {
        return getList(String.format(
                "%s/find-replaceable", url
        ));
    }

    private List<Passport> getList(String url) {
        List<Passport> body = client.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Passport>>() {
                }
        ).getBody();
        return body == null ? Collections.emptyList() : body;
    }
}
