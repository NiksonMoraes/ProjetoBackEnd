package com.Integrador.ProjetoBackEnd.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class NotaFiscal extends Atendimentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
