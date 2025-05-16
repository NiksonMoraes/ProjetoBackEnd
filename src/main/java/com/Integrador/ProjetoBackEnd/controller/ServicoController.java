package com.Integrador.ProjetoBackEnd.controller;

import com.Integrador.ProjetoBackEnd.entities.Barbeiro;
import com.Integrador.ProjetoBackEnd.entities.Servico;
import com.Integrador.ProjetoBackEnd.repository.ServicoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicoController {
    private final ServicoRepository repository;

    public ServicoController(ServicoRepository repository){
        this.repository = repository;
    }

    @GetMapping
    public List<Servico> listar(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Servico buscar(@PathVariable Long id){
        return repository.findById(id).orElseThrow();
    }

    @PostMapping
    public Servico criar(@RequestBody Servico servico){
        return repository.save(servico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servico> atualizar(@PathVariable Long id, @RequestBody Servico dados){
        return repository.findById(id).map(servico -> {
            if (dados.getNome() != null) servico.setNome(dados.getNome());
            if (dados.getPreco() != null) servico.setPreco(dados.getPreco());
            if (dados.getDuracao() != null) servico.setDuracao(dados.getDuracao());

            Servico servicoAtualizado = repository.save(servico);
            return ResponseEntity.ok(servicoAtualizado);
        }).orElse(ResponseEntity.notFound().build());
    }

    public void deletar(@PathVariable Long id){
        repository.deleteById(id);
    }

}
