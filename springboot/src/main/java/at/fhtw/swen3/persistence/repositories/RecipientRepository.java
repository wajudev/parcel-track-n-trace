package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface RecipientRepository extends JpaRepository<RecipientEntity, Long> {

    RecipientEntity findByName(String name);
    //@Transactional
    void deleteByName(String name);
}
