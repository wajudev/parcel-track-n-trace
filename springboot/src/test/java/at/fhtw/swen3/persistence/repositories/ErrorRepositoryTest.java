package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.ErrorEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ErrorRepositoryTest {

    @Autowired
    public ErrorRepository errorRepository;

    @Test
    void insertError(){
        ErrorEntity error = new ErrorEntity();
        error.setErrorMessage("Error:404");
        errorRepository.save(error);
        assertEquals(error.getErrorMessage(),errorRepository.findById("Error:404").get().getErrorMessage());
        errorRepository.deleteById(error.getErrorMessage());
    }
}
