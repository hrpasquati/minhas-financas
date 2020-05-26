package com.pasquati.minhasfinancas.services.impl;

import com.pasquati.minhasfinancas.exception.ErroAutenticacao;
import com.pasquati.minhasfinancas.exception.RegraNegocioException;
import com.pasquati.minhasfinancas.model.entity.Usuario;
import com.pasquati.minhasfinancas.repositories.UsuarioRepository;
import com.pasquati.minhasfinancas.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public Usuario autentica(String email, String senha) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if (!usuario.isPresent()) {
            throw new ErroAutenticacao("Usuario não encontrado para o email informado");
        }
        if (usuario.get().getSenha().equals(senha)) {
            throw new ErroAutenticacao("Senha inválida");
        }
        return usuario.get();
    }

    @Override
    @Transactional
    public Usuario insert(Usuario usuario) {
        validarEmail(usuario.getEmail());
        return usuarioRepository.save(usuario);
    }

    @Override
    public void validarEmail(String email) {
        boolean existe = usuarioRepository.existsByEmail(email);
        if (existe) {
            throw new RegraNegocioException("Já existe um usuário cadastro com esse e-mail.");
        }

    }

    public Usuario findById(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.orElse(null);
    }


}
