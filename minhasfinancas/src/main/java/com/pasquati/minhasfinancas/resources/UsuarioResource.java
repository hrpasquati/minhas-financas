package com.pasquati.minhasfinancas.resources;

import com.pasquati.minhasfinancas.DTO.UsuarioDTO;
import com.pasquati.minhasfinancas.exception.ErroAutenticacao;
import com.pasquati.minhasfinancas.exception.RegraNegocioException;
import com.pasquati.minhasfinancas.model.entity.Usuario;
import com.pasquati.minhasfinancas.services.LancamentoService;
import com.pasquati.minhasfinancas.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LancamentoService lancamentoService;


    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody UsuarioDTO usuarioDTO) {

        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());

        try {
            Usuario usuariroSalvo = usuarioService.insert(usuario);
            return new ResponseEntity(usuariroSalvo, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @PostMapping(value = "/autenticar")
    public ResponseEntity autenticar(@RequestBody UsuarioDTO usuarioDTO) {
           try{
              Usuario usuarioAutenticado =  usuarioService.autentica(usuarioDTO.getEmail(), usuarioDTO.getSenha());
                return ResponseEntity.ok(usuarioAutenticado);
           } catch (ErroAutenticacao e){
                    return ResponseEntity.badRequest().body(e.getMessage());
           }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id){
        Usuario usuario = usuarioService.findById(id);
        return ResponseEntity.ok(usuario);
    }
}
