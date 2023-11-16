package com.apienviofotos.apienviofotos.repository;

import com.apienviofotos.apienviofotos.model.Inspecoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface InspecaoRepository extends JpaRepository<Inspecoes, Long> {
    Optional<Inspecoes> findByid(Long id);

    @Query("select i from Inspecoes i where i.id = :id")
    Optional<Inspecoes> findSeguradoraById(@Param("id") Long id);
}
