package com.pasquati.minhasfinancas.repositories;

import com.pasquati.minhasfinancas.model.entity.Lancamento;
import com.pasquati.minhasfinancas.model.enums.TipoLancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

    @Query(value = "select sum(l.valor) from Lancamento l join l.usuario u where u.id = :idUsuario and l.tipo = :tipo group by u")
    BigDecimal obterSaldoPorTipoLancamentoEUsuario(@Param(value = "idUsuario") Long idUsuario, @Param(value = "tipo") TipoLancamento tipo);


}
