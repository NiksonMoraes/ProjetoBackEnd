package com.Integrador.ProjetoBackEnd.controller;

import com.Integrador.ProjetoBackEnd.entities.Clientes;
import com.Integrador.ProjetoBackEnd.repository.ClienteRepository;
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
    public List<Clientes> listarTodos(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Clientes buscarPorId(@PathVariable Long id){
        return repository.findById(id).orElseThrow();
    }

    @PostMapping
    public Clientes criar(@ResponseBody Clientes cliente){
        return repository.save(cliente);
    }

    @PutMapping("/{id}")
    public Clientes atualizar(@PathVariable Long id, @RequestBody Clientes cliente){
        return repository.save(cliente);
    }



}
