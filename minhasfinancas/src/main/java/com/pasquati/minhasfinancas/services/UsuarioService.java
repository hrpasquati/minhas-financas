package com.pasquati.minhasfinancas.services;

import com.pasquati.minhasfinancas.model.entity.Usuario;

public interface UsuarioService {

    Usuario autentica (String email, String senha);

    Usuario salvarUsuario (Usuario usuario);

    void validarEmail(String email);
}
