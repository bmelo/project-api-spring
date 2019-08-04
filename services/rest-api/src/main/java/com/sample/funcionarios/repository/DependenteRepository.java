package com.sample.funcionarios.repository;

import com.sample.funcionarios.models.Dependente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DependenteRepository extends JpaRepository<Dependente, Long> {
    List<Dependente> findByFuncionarioId(Long funcionarioId);
}
