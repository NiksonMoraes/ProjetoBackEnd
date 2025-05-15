package com.Integrador.ProjetoBackEnd.controller;

import com.Integrador.ProjetoBackEnd.entities.Cliente;
import com.Integrador.ProjetoBackEnd.repository.ClienteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteRepository repository;

    public ClienteController(ClienteRepository repository){
        this.repository = repository;
    }

    @GetMapping
    public List<Cliente> listarTodos(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Cliente buscarPorId(@PathVariable Long id){
        return repository.findById(id).orElseThrow();
    }

    @PostMapping
    public Cliente criar(@ResponseBody Cliente cliente){
        return repository.save(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente dados){
        return repository.findById(id).map(Cliente cliente -> {
            if (dados.getNome() != null) cliente.setNome(dados.getNome());
            if (dados.getEmail() != null) cliente.setEmail(dados.getNome());
            if (dados.getTelefone() != null) cliente.setTelefone(dados.getNome());

            Cliente clienteAtualizado = repository.save(cliente);
            return ResponseEntity.ok(clienteAtualizado);
        }).orElse(ResponseEntity.notFound().build());
    }

    public void deletar(@PathVariable Long id){
        repository.deleteById(id);
    }

}
