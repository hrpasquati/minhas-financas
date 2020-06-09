package com.pasquati.minhasfinancas.services.impl;

import com.pasquati.minhasfinancas.DTO.LancamentoDTO;
import com.pasquati.minhasfinancas.exception.RegraNegocioException;
import com.pasquati.minhasfinancas.model.entity.Lancamento;
import com.pasquati.minhasfinancas.model.entity.Usuario;
import com.pasquati.minhasfinancas.model.enums.StatusLancamento;
import com.pasquati.minhasfinancas.model.enums.TipoLancamento;
import com.pasquati.minhasfinancas.repositories.LancamentoRepository;
import com.pasquati.minhasfinancas.services.LancamentoService;
import com.pasquati.minhasfinancas.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LancamentoServiceImpl implements LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private UsuarioService usuarioService;


    @Override
    @Transactional
    public Lancamento salvar(Lancamento lancamento) {
        validar(lancamento);
        lancamento.setStatusLancamento(StatusLancamento.PENDENTES);
        return lancamentoRepository.save(lancamento);
    }

    @Override
    @Transactional
    public Lancamento atualizar(Lancamento lancamento) {
        validar(lancamento);
        Objects.requireNonNull(lancamento.getId());
        return lancamentoRepository.save(lancamento);
    }

    @Override
    @Transactional
    public void deletar(Lancamento lancamento) {
        Objects.requireNonNull(lancamento.getId());
        lancamentoRepository.delete(lancamento);

    }

    @Override
    @Transactional(readOnly = true)
    public List<Lancamento> buscar(Lancamento lancamentoFiltro) {
        Example example = Example.of(lancamentoFiltro,
                ExampleMatcher.matching().withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));


        return lancamentoRepository.findAll(example);
    }

    @Override
    public void atualizarStatus(Lancamento lancamento, StatusLancamento statusLancamento) {
        lancamento.setStatusLancamento(statusLancamento);
        atualizar(lancamento);

    }

    @Override
    public void validar(Lancamento lancamento) {

        if (lancamento.getDescricao() == null || lancamento.getDescricao().trim().equals("")) {
            throw new RegraNegocioException("Informe uma Descricao valida");
        }

        if (lancamento.getMes() == null || lancamento.getMes() < 1 || lancamento.getMes() > 12) {
            throw new RegraNegocioException("Informe um mês válido");
        }

        if (lancamento.getAno() == null || lancamento.getAno().toString().length() != 4) {
            throw new RegraNegocioException("Informe um Ano válido");
        }

        if (lancamento.getUsuario() == null || lancamento.getUsuario().getId() == null) {
            throw new RegraNegocioException("Informe um Usuário");
        }

        if (lancamento.getValor() == null || lancamento.getValor().compareTo(BigDecimal.ZERO) < 1) {
            throw new RegraNegocioException("Informe um valor válido");
        }

        if (lancamento.getTipo() == null) {
            throw new RegraNegocioException("Informe um tipo de Lancamento");
        }
    }

    public Lancamento fromDTO(LancamentoDTO lancamentoDTO) {

        Lancamento lancamento = new Lancamento();
        lancamento.setId(lancamentoDTO.getId());
        lancamento.setDescricao(lancamentoDTO.getDescricao());
        lancamento.setAno(lancamentoDTO.getAno());
        lancamento.setMes(lancamentoDTO.getMes());
        lancamento.setValor(lancamentoDTO.getValaor());
        Usuario usuario = usuarioService.findById(lancamentoDTO.getUsuario());

        lancamento.setUsuario(usuario);
        if (lancamentoDTO.getUsuario() == null) {
            lancamento.setTipo(TipoLancamento.valueOf(lancamentoDTO.getTipo()));
        }
        if (lancamentoDTO.getStatus() == null) {
            lancamento.setStatusLancamento(StatusLancamento.valueOf(lancamentoDTO.getStatus()));
        }

        return lancamento;
    }

    @Override
    public Optional<Lancamento> findById(Long id) {
        return lancamentoRepository.findById(id);
    }

    @Override
    public BigDecimal obterSaldoPorUsuario(Long id) {
        BigDecimal receita = lancamentoRepository.obterSaldoPorTipoLancamentoEUsuario(id, TipoLancamento.RECEITA);
        BigDecimal despesa = lancamentoRepository.obterSaldoPorTipoLancamentoEUsuario(id, TipoLancamento.DESPESA);

        if (receita == null){
            receita = BigDecimal.ZERO;
        }

        if (despesa == null) {
            despesa = BigDecimal.ZERO;
        }

        return receita.subtract(despesa);
    }

}
