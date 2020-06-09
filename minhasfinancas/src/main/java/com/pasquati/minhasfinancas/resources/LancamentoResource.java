package com.pasquati.minhasfinancas.resources;

import com.pasquati.minhasfinancas.DTO.AtualizaStatusDTO;
import com.pasquati.minhasfinancas.DTO.LancamentoDTO;
import com.pasquati.minhasfinancas.exception.RegraNegocioException;
import com.pasquati.minhasfinancas.model.entity.Lancamento;
import com.pasquati.minhasfinancas.model.entity.Usuario;
import com.pasquati.minhasfinancas.model.enums.StatusLancamento;
import com.pasquati.minhasfinancas.services.LancamentoService;
import com.pasquati.minhasfinancas.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/lancamentos")
public class LancamentoResource {

    @Autowired
    private LancamentoService lancamentoService;

    @Autowired
    private UsuarioService usuarioService;


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

    @PutMapping("/{id}/atualiza-status")
    public ResponseEntity atualizarStatus (@PathVariable Long id, @RequestBody AtualizaStatusDTO atualizaStatusDTO) {
        return lancamentoService.findById(id).map( entity -> {
            StatusLancamento statusSelecionado = StatusLancamento.valueOf(atualizaStatusDTO.getStatus());
            if (statusSelecionado == null){
                return ResponseEntity.badRequest().build();
            }
                entity.setStatusLancamento(statusSelecionado);
                lancamentoService.atualizar(entity);
                return ResponseEntity.ok().build();

        }).orElseGet(() -> new ResponseEntity("Lancamento nao encontrado", HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        lancamentoService.findById(id).map(entity -> {
            lancamentoService.deletar(entity);
            return ResponseEntity.noContent().build();
        });
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<Lancamento> buscar (@RequestParam(value = "descricao", required = false) String descricao,
                                  @RequestParam(value = "mes", required = false) Integer mes,
                                  @RequestParam (value = "ano", required = false) Integer ano,
                                  @RequestParam(value = "usuario") Long id){
        Lancamento lancamentoFiltro = new Lancamento();
        lancamentoFiltro.setDescricao(descricao);
        lancamentoFiltro.setMes(mes);
        lancamentoFiltro.setAno(ano);

        Usuario usuario = usuarioService.findById(id);

        if (usuario == null) {
            return ResponseEntity.badRequest().build();
        } else{
            lancamentoFiltro.setUsuario(usuario);
        }
        List<Lancamento> lancamentos = lancamentoService.buscar(lancamentoFiltro);

        return ResponseEntity.ok().build();

    }

    }

