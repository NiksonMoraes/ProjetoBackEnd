package com.Integrador.ProjetoBackEnd.repository;

import com.Integrador.ProjetoBackEnd.entities.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
}
