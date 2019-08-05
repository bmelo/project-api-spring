package com.sample.funcionarios.controllers;

import com.sample.funcionarios.exception.ResourceNotFoundException;
import com.sample.funcionarios.models.Dependente;
import com.sample.funcionarios.repository.DependenteRepository;
import com.sample.funcionarios.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
public class DependenteController {

    @Autowired
    private DependenteRepository dependenteRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping("/v1/funcionarios/{funcionarioId}/dependentes")
    public List<Dependente> getDependentesByFuncionarioId(@PathVariable Long funcionarioId) {
        return dependenteRepository.findByFuncionarioId(funcionarioId);
    }

    @PostMapping("/v1/funcionarios/{funcionarioId}/dependentes")
    public Dependente addDependente(@PathVariable Long funcionarioId,
                            @Valid @RequestBody Dependente dependente) {
        return funcionarioRepository.findById(funcionarioId)
                .map(funcionario -> {
                    dependente.setFuncionario(funcionario);
                    return dependenteRepository.save(dependente);
                }).orElseThrow(() -> new ResourceNotFoundException("Funcionario not found with id " + funcionarioId));
    }

    @PutMapping("/v1/funcionarios/{funcionarioId}/dependentes/{dependenteId}")
    public Dependente updateDependente(@PathVariable Long funcionarioId,
                               @PathVariable Long dependenteId,
                               @Valid @RequestBody Dependente dependenteRequest) {
        if(!funcionarioRepository.existsById(funcionarioId)) {
            throw new ResourceNotFoundException("Funcionario not found with id " + funcionarioId);
        }

        return dependenteRepository.findById(dependenteId)
                .map(dependente -> {
                    dependente.setNome(dependenteRequest.getNome());
                    return dependenteRepository.save(dependente);
                }).orElseThrow(() -> new ResourceNotFoundException("Dependente not found with id " + dependenteId));
    }

    @DeleteMapping("/v1/funcionarios/{funcionarioId}/dependentes/{dependenteId}")
    public ResponseEntity<?> deleteDependente(@PathVariable Long funcionarioId,
                                          @PathVariable Long dependenteId) {
        if(!funcionarioRepository.existsById(funcionarioId)) {
            throw new ResourceNotFoundException("Funcionario not found with id " + funcionarioId);
        }

        return dependenteRepository.findById(dependenteId)
                .map(dependente -> {
                    dependenteRepository.delete(dependente);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Dependente not found with id " + dependenteId));

    }
}
