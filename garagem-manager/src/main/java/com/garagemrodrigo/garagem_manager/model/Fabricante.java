package com.garagemrodrigo.garagem_manager.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "fabricante")
public class Fabricante {
    @Id
    private Integer id;

    @Column
    private String name;

    @Column 
    private LocalDate createdDate;

}
