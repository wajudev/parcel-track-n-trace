package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.services.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ParcelServiceImpl implements ParcelService {
    @Autowired
    private ParcelRepository parcelRepository;

    public ParcelServiceImpl(ParcelRepository parcelRepository) {
        this.parcelRepository = parcelRepository;
    }

    public List<ParcelEntity> getAllParcel(){
        return parcelRepository.findAll();
    }

}
