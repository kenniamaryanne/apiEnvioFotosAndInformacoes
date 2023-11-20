package com.apienviofotos.apienviofotos.InspecaoController;

import com.apienviofotos.apienviofotos.model.Fotos_inspecao;
import com.apienviofotos.apienviofotos.model.InspecaoRemota;
import com.apienviofotos.apienviofotos.service.InspecaoService;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.*;

@RestController
@RequestMapping("/inspecoes")
public class InspecaoController {

    @Autowired
    private InspecaoService inspecaoService;

    @PostMapping("/processar")
    public ResponseEntity<?> processarInspecoes(@RequestParam("foto") MultipartFile foto,
                                                     @RequestParam("descricao") String descricao,
                                                     @RequestParam("latitude") String latitude,
                                                     @RequestParam("longitude") String longitude,
                                                     @RequestParam("observacao") String observacao,
                                                     @RequestParam("tipo") String tipo,
                                                     @RequestParam("vistoria") Long vistoria,
                                                     @RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        try {

            InspecaoRemota inspecaoRemota = new InspecaoRemota();
            inspecaoRemota.setVistoria(vistoria);
            inspecaoRemota.setFoto(foto.getBytes());
            inspecaoRemota.setDescricao(descricao);
            inspecaoRemota.setLatitude(latitude);
            inspecaoRemota.setLongitude(longitude);
            inspecaoRemota.setObservacao(observacao);
            inspecaoRemota.setData(data);
            inspecaoRemota.setTipo(tipo);

            inspecaoService.processarJson(inspecaoRemota);


            return ResponseEntity.ok(inspecaoRemota);
        } catch (IOException e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro no processamento do JSON");
        }
    }

    @GetMapping("/buscar-por-vistoria/{codigoVistoria}")
    public ResponseEntity<List<Fotos_inspecao>> buscarPorVistoria(@PathVariable Integer codigoVistoria) {
        List<Fotos_inspecao> fotos = inspecaoService.buscarPorVistoria(codigoVistoria);
        return ResponseEntity.ok(fotos);
    }


}
