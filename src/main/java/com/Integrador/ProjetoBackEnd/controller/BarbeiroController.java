package com.Integrador.ProjetoBackEnd.controller;

import com.Integrador.ProjetoBackEnd.entities.Agendamento;
import com.Integrador.ProjetoBackEnd.entities.Barbeiro;
import com.Integrador.ProjetoBackEnd.repository.AgendamentoRepository;
import com.Integrador.ProjetoBackEnd.repository.BarbeiroRepository;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/barbeiro")
public class BarbeiroController {
    private final BarbeiroRepository repository;
    private final AgendamentoRepository agendamentoRepository;

    public BarbeiroController(BarbeiroRepository repository, AgendamentoRepository agendamentoRepository) {
        this.repository = repository;
        this.agendamentoRepository = agendamentoRepository;
    }

    @GetMapping
    public List<Barbeiro> listar(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Barbeiro buscar(@PathVariable Long id){
        return repository.findById(id).orElseThrow();
    }

    @PostMapping
    public Barbeiro criar(@RequestBody Barbeiro barbeiro){
        return repository.save(barbeiro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Barbeiro> atualizar(@PathVariable Long id, @RequestBody Barbeiro dados){
        return repository.findById(id).map(barbeiro -> {
            if (dados.getNome() != null) barbeiro.setNome(dados.getNome());
            if (dados.getEspecialidade() != null) barbeiro.setEspecialidade(dados.getEspecialidade());

            Barbeiro barbeiroAtualizado = repository.save(barbeiro);
            return ResponseEntity.ok(barbeiroAtualizado);
        }).orElse(ResponseEntity.notFound().build());
    }

    public void deletar(@PathVariable Long id){
        repository.deleteById(id);
    }

    @GetMapping("/{id}/disponibilidade")
    public List<LocalDateTime> disponibilidade(@PathVariable Long id, @RequestParam LocalDate data){
        LocalDateTime inicio = data.atStartOfDay();
        LocalDateTime fim = data.plusDays(1).atStartOfDay();
        List<Agendamento> agendamentos = agendamentoRepository.findByBarbeiroIdAndDataHoraBetween(id, inicio,fim);

        List<LocalDateTime> horario = new ArrayList<>();
        for(int hora = 9; hora < 18; hora++) {
            LocalDateTime slot = data.atTime(hora, 0);
            boolean ocupado = agendamentos.stream().anyMatch(a -> a.getDataHora().equals(slot));
            if(!ocupado) horario.add(slot);
        }
        return horario;

    }

}
