package com.Integrador.ProjetoBackEnd.controller;

import com.Integrador.ProjetoBackEnd.entities.Barbeiro;
import com.Integrador.ProjetoBackEnd.entities.Cliente;
import com.Integrador.ProjetoBackEnd.repository.BarbeiroRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/barbeiro")
public class BarbeiroController {
    private final BarbeiroRepository repository;

    public BarbeiroController(BarbeiroRepository repository) {
        this.repository = repository;
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


}
