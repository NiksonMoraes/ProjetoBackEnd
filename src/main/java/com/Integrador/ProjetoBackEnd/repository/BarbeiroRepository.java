package com.Integrador.ProjetoBackEnd.repository;

import com.Integrador.ProjetoBackEnd.entities.Barbeiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarbeiroRepository extends JpaRepository<Barbeiro, Long> {
}
