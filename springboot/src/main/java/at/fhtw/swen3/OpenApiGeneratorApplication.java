package at.fhtw.swen3;

import com.fasterxml.jackson.databind.Module;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication()
@Slf4j
@ComponentScan(basePackages = {"org.openapitools", "at.fhtw.swen3.services" , "at.fhtw.swen3.configuration",
        "at.fhtw.swen3.persistence.repositories","at.fhtw.swen3.controller", "at.fhtw.swen3.persistence.entities"})

public class OpenApiGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenApiGeneratorApplication.class, args);
        log.info("Starting Application");
    }

    @Bean
    public Module jsonNullableModule() {
        return new JsonNullableModule();
    }

}