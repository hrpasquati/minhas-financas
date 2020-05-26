package com.pasquati.minhasfinancas.resources;

import com.pasquati.minhasfinancas.DTO.LancamentoDTO;
import com.pasquati.minhasfinancas.exception.RegraNegocioException;
import com.pasquati.minhasfinancas.model.entity.Lancamento;
import com.pasquati.minhasfinancas.services.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/lancamentos")
public class LancamentoResource {

    @Autowired
    private LancamentoService lancamentoService;


    @PostMapping // toda vez que for criar um novo recurso
    public ResponseEntity<Void> insert(@RequestBody LancamentoDTO lancamentoDTO) {
        try {
            Lancamento lancamento = lancamentoService.fromDTO(lancamentoDTO);
            lancamentoService.salvar(lancamento);
            return ResponseEntity.noContent().build();
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody LancamentoDTO lancamentoDTO, @PathVariable Long id) {
        lancamentoService.findById(id).map(entity -> {
            Lancamento lancamento = lancamentoService.fromDTO(lancamentoDTO);
            lancamento.setId(entity.getId());
            lancamentoService.atualizar(lancamento);
            return ResponseEntity.ok().build();
        });
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        lancamentoService.findById(id).map(entity -> {
            lancamentoService.deletar(entity);
            return ResponseEntity.noContent().build();
        });
        return ResponseEntity.badRequest().build();
    }

    }

