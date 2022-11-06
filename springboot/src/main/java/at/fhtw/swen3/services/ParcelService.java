package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;

import java.util.List;

public interface ParcelService {

    List<ParcelEntity> getAllParcel();

}
