package com.sample.funcionarios.controllers;

import com.sample.funcionarios.exception.ResourceNotFoundException;
import com.sample.funcionarios.models.Funcionario;
import com.sample.funcionarios.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository FuncionarioRepository;

    @GetMapping("/v1/funcionarios")
    public Page<Funcionario> getFuncionarios(Pageable pageable) {
        return FuncionarioRepository.findAll(pageable);
    }


    @PostMapping("/v1/funcionarios")
    public Funcionario createFuncionario(@Valid @RequestBody Funcionario Funcionario) {
        return FuncionarioRepository.save(Funcionario);
    }

    @PutMapping("/v1/funcionarios/{FuncionarioId}")
    public Funcionario updateFuncionario(@PathVariable Long FuncionarioId,
                                   @Valid @RequestBody Funcionario FuncionarioRequest) {
        return FuncionarioRepository.findById(FuncionarioId)
                .map(Funcionario -> {
                    Funcionario.setNome(FuncionarioRequest.getNome());
                    Funcionario.setSetor(FuncionarioRequest.getSetor());
                    return FuncionarioRepository.save(Funcionario);
                }).orElseThrow(() -> new ResourceNotFoundException("Funcionario not found with id " + FuncionarioId));
    }


    @DeleteMapping("/v1/funcionarios/{FuncionarioId}")
    public ResponseEntity<?> deleteFuncionario(@PathVariable Long FuncionarioId) {
        return FuncionarioRepository.findById(FuncionarioId)
                .map(Funcionario -> {
                    FuncionarioRepository.delete(Funcionario);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Funcionario not found with id " + FuncionarioId));
    }
}
