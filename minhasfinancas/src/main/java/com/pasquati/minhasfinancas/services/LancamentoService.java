package com.pasquati.minhasfinancas.services;

import com.pasquati.minhasfinancas.DTO.LancamentoDTO;
import com.pasquati.minhasfinancas.model.entity.Lancamento;
import com.pasquati.minhasfinancas.model.enums.StatusLancamento;

import java.util.List;
import java.util.Optional;

public interface LancamentoService {

    Lancamento salvar(Lancamento lancamento);

    Lancamento atualizar(Lancamento lancamento);

    void deletar(Lancamento lancamento);

    List<Lancamento> buscar (Lancamento lancamentoFiltro);

    void atualizarStatus (Lancamento lancamento, StatusLancamento statusLancamento);

    void validar( Lancamento lancamento);

    Lancamento fromDTO(LancamentoDTO lancamentoDTO);

    Optional<Lancamento> findById(Long id);
}
