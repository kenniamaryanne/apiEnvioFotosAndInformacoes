package com.apienviofotos.apienviofotos.service;

import com.apienviofotos.apienviofotos.model.Fotos_inspecao;
import com.apienviofotos.apienviofotos.model.InspecaoRemota;
import com.apienviofotos.apienviofotos.model.Inspecoes;
import com.apienviofotos.apienviofotos.model.Pessoas;
import com.apienviofotos.apienviofotos.repository.InspecaoRepository;
import com.apienviofotos.apienviofotos.repository.PessoaRepository;
import com.apienviofotos.apienviofotos.repository.FotosInspecaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import java.util.List;
import java.time.LocalDate;

@Service
public class InspecaoService {

    @Autowired
    private InspecaoRepository inspecaoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private FotosInspecaoRepository fotosInspecaoRepository;

    public void processarJson(InspecaoRemota inspecoes) throws IOException {
//        for (InspecaoRemota inspecao : inspecoes) {
//
//        }

        processarInspecao(inspecoes);
    }

    private void processarInspecao(InspecaoRemota inspecao) throws IOException {
        String tipo = inspecao.getTipo();
        String seguradora = buscarSeguradora(inspecao.getVistoria());
        String pastaTipo = tipo.equals("AV") ? "autovistoria" : "vistoriaRemota";

        String diretorioImagens = "\\\\ultron\\Zarus Tecnologia\\FotosVistoriasOnline" + File.separator+ pastaTipo;

        String caminhoSeguradora = diretorioImagens + File.separator + seguradora;

        File diretorioSeguradora = new File(caminhoSeguradora);
        if (!diretorioSeguradora.exists()) {
            boolean criadoSeguradora = diretorioSeguradora.mkdirs();
        }

        LocalDate dataAtual = LocalDate.now();
        int anoAtual = dataAtual.getYear();

        String caminhoAno = caminhoSeguradora + File.separator + anoAtual;

        File diretorioAno = new File(caminhoAno);
        if (!diretorioAno.exists()) {
            boolean criadoAno = diretorioAno.mkdirs();
        }

        DateTimeFormatter formatadorMes = DateTimeFormatter.ofPattern("MMMM");
        String nomeMes = dataAtual.format(formatadorMes);

        String caminhoMes = caminhoSeguradora + File.separator + anoAtual +File.separator+ nomeMes;

        File diretorioMes = new File(caminhoMes);

        if (!diretorioMes.exists()) {
            boolean criadoMes = diretorioMes.mkdirs();
        }

        String caminhoVistoria = caminhoSeguradora + File.separator + anoAtual + File.separator+ nomeMes + File.separator + "vistoria_"+inspecao.getVistoria();

        File diretorioVistoria = new File(caminhoVistoria);

        if (!diretorioVistoria.exists()) {
            boolean criadoVistoria = diretorioVistoria.mkdirs();
        }

        String nomeArquivo = "foto_vistoria" + inspecao.getVistoria()+"foto_" +inspecao.getDescricao()+System.currentTimeMillis() + ".png";
        byte[] bytes = inspecao.getFoto();
        Path path = Paths.get(caminhoVistoria,nomeArquivo);
        Files.write(path, bytes);

        salvarInspecao(inspecao,caminhoVistoria);

    }

    private String buscarSeguradora(Long codigoVistoria) {
        Optional<Inspecoes> inspecaoOptional = inspecaoRepository.findByid(codigoVistoria);

        if (inspecaoOptional.isPresent()) {
            Inspecoes inspecao = inspecaoOptional.get();

            Optional<Pessoas> pessoas  = pessoaRepository.findByid(inspecao.getSeguradora());


            String nomeSeguradora = buscarNomeSeguradoraPorCodigo(pessoas.get().getId());

            return nomeSeguradora;
        }

        return "Seguradora não encontrada";
    }


    public List<Fotos_inspecao> buscarPorVistoria(Integer codigoVistoria) {
        return fotosInspecaoRepository.findByVistoria(codigoVistoria);
    }


    private void salvarInspecao(InspecaoRemota inspecao, String caminhoFoto) {

        Fotos_inspecao envio_inspecao = new Fotos_inspecao();
        envio_inspecao.setVistoria(inspecao.getVistoria());
        envio_inspecao.setCaminho(caminhoFoto);
        envio_inspecao.setDescricao(inspecao.getDescricao());
        envio_inspecao.setLatitude(inspecao.getLatitude());
        envio_inspecao.setLongitude(inspecao.getLongitude());
        envio_inspecao.setObservacao(inspecao.getObservacao());
        envio_inspecao.setTipo(inspecao.getTipo());

        fotosInspecaoRepository.save(envio_inspecao);


    }

    private String buscarNomeSeguradoraPorCodigo(Long codigoSeguradora) {
        Optional<Pessoas> pessoaOptional =  pessoaRepository.findByid(codigoSeguradora);

        return pessoaOptional.map(Pessoas::getNome)
                .orElse("Nome da seguradora não encontrado");
    }







}
