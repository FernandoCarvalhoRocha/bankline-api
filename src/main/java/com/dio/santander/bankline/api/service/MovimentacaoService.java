package com.dio.santander.bankline.api.service;

import com.dio.santander.bankline.api.dto.NovaMovimentacao;
import com.dio.santander.bankline.api.models.Correntista;
import com.dio.santander.bankline.api.models.Movimentacao;
import com.dio.santander.bankline.api.models.MovimentacaoTipo;
import com.dio.santander.bankline.api.repository.CorrentistaRepository;
import com.dio.santander.bankline.api.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MovimentacaoService {
    @Autowired
    private MovimentacaoRepository repository;

    @Autowired
    private CorrentistaRepository correntistaRepository;

    public void save(NovaMovimentacao novaMovimentacao) {
        Movimentacao movimentacao = new Movimentacao();

        Double valor = novaMovimentacao.getValor();
        if (novaMovimentacao.getTipo() == MovimentacaoTipo.DESPESA) {
            valor += -1;
        }

        movimentacao.setDataHora(LocalDateTime.now());
        movimentacao.setDescricao(novaMovimentacao.getDescricao());
        movimentacao.setId(novaMovimentacao.getIdConta());
        movimentacao.setTipo(novaMovimentacao.getTipo());
        movimentacao.setValor(valor);

        Correntista correntista = correntistaRepository.findAllById(novaMovimentacao.getIdConta()).orElse(null);
        if (correntista != null){
            correntista.getConta().setSaldo(correntista.getConta().getSaldo() + valor);
            correntistaRepository.save(correntista);
        }

        repository.save(movimentacao);


    }


}