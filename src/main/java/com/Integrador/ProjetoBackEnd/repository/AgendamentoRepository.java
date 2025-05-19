package com.Integrador.ProjetoBackEnd.repository;

import com.Integrador.ProjetoBackEnd.entities.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    List<Agendamento> findByBarbeiroIdAndDataHoraBetween(Long barbeiroId, LocalDateTime inicio, LocalDateTime fim);
}
