package com.example.carros.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.convert.DataSizeUnit;

@Entity
@Table(name = "carro")
@Data
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String tipo;
}
