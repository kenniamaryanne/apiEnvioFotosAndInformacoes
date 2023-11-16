package com.apienviofotos.apienviofotos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

import com.apienviofotos.apienviofotos.model.Fotos_inspecao;

public interface FotosInspecaoRepository extends JpaRepository<Fotos_inspecao, Long> {
    List<Fotos_inspecao> findByVistoria(Integer codigoVistoria);

}