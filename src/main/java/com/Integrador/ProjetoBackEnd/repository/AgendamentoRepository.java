package com.Integrador.ProjetoBackEnd.repository;

import com.Integrador.ProjetoBackEnd.entities.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}
