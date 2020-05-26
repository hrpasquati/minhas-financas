package com.pasquati.minhasfinancas.services;

import com.pasquati.minhasfinancas.model.entity.Usuario;

public interface UsuarioService {

    Usuario autentica(String email, String senha);

    void validarEmail(String email);

    Usuario insert (Usuario usuario);

    Usuario findById(Long id);

}
