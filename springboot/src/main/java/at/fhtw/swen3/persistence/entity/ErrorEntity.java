package at.fhtw.swen3.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ErrorEntity {
    @Id
    @Column
    private String errorMessage;
}
