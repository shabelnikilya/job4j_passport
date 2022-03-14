package ru.job4j;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@SpringBootApplication
@EnableScheduling
public class Job4jPassportApplication {

    @Bean
    public RestTemplate getTemplate() {
        return new RestTemplate();
    }

    @Bean()
    public Gson getGson() {
        return new GsonBuilder().create();
    }

    @Bean
    public SpringLiquibase liquibase(DataSource ds) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:liquibase-changeLog.xml");
        liquibase.setDataSource(ds);
        return liquibase;
    }

    public static void main(String[] args) {
        SpringApplication.run(Job4jPassportApplication.class, args);
    }

}
