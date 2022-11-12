package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Configuration
public interface ParcelService {

    List<ParcelEntity> getAllParcel();

}
