package ru.job4j.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "passports")
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(mappedBy = "passport")
    private People people;
    private String series;
    private String number;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Temporal(TemporalType.TIMESTAMP)
    private Date expired;

    public Passport() {
    }

    public Passport(String series, String number) {
        this.series = series;
        this.number = number;
    }

    public Passport(String series, String number, Date created) {
        this.series = series;
        this.number = number;
        this.created = created;
    }

    public Passport(int id, String series, String number, Date created, Date expired) {
        this.id = id;
        this.series = series;
        this.number = number;
        this.created = created;
        this.expired = expired;
    }

    public Passport(int id, People people, String series, String number, Date created, Date expired) {
        this.id = id;
        this.people = people;
        this.series = series;
        this.number = number;
        this.created = created;
        this.expired = expired;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Passport passport = (Passport) o;
        return id == passport.id && Objects.equals(series, passport.series);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, series);
    }
}
