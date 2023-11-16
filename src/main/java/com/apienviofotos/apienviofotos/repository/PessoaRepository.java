package com.apienviofotos.apienviofotos.repository;

import com.apienviofotos.apienviofotos.model.Inspecoes;
import com.apienviofotos.apienviofotos.model.Pessoas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoas, Long> {
    Optional<Pessoas> findByid(Long vistoria);

    @Query("select i from Pessoas i where i.id = :id")
    Optional<Pessoas> findNomeSeguradoraById(@Param("id") Long id);

}
