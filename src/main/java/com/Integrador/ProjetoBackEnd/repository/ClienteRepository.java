package com.Integrador.ProjetoBackEnd.repository;

import com.Integrador.ProjetoBackEnd.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository <Cliente, Long> {

}
