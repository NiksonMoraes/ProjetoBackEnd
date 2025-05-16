package com.Integrador.ProjetoBackEnd.controller;

import com.Integrador.ProjetoBackEnd.entities.Agendamento;
import com.Integrador.ProjetoBackEnd.entities.Servico;
import com.Integrador.ProjetoBackEnd.repository.AgendamentoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {
    private final AgendamentoRepository repository;

    public AgendamentoController(AgendamentoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Agendamento> listar(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Agendamento buscar(@PathVariable Long id){
        return repository.findById(id).orElseThrow();
    }

    @PostMapping
    public Agendamento criar(@RequestBody Agendamento agendamento){
        return repository.save(agendamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agendamento> atualizar(@PathVariable Long id, @RequestBody Agendamento dados){
        return repository.findById(id).map(agendamento -> {
            if (dados.getCliente() != null) agendamento.setCliente(dados.getCliente());
            if (dados.getBarbeiro() != null) agendamento.setBarbeiro(dados.getBarbeiro());
            if (dados.getServico() != null) agendamento.setServico(dados.getServico());

            Agendamento agendamentoAtualizado = repository.save(agendamento);
            return ResponseEntity.ok(agendamentoAtualizado);
        }).orElse(ResponseEntity.notFound().build());
    }

    public void deletar(@PathVariable Long id){
        repository.deleteById(id);
    }

}
