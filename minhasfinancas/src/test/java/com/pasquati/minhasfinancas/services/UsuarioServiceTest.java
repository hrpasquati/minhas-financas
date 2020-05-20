package com.pasquati.minhasfinancas.services;

import com.pasquati.minhasfinancas.exception.RegraNegocioException;
import com.pasquati.minhasfinancas.repositories.UsuarioRepository;
import com.pasquati.minhasfinancas.services.impl.UsuarioServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {


    private UsuarioService usuarioService;
    @MockBean
    private UsuarioRepository usuarioRepository;

    @Before
    public void setUp() {
        usuarioService = new UsuarioServiceImpl(usuarioRepository);
    }

    @Test(expected = Test.None.class)
    public void deveValidarEmail() {
        //cenario
        Mockito.when(usuarioRepository.existsByEmail(Mockito.anyString())).thenReturn(false);

        //acao
        usuarioService.validarEmail("usuario@email.com");
    }

    @Test(expected = RegraNegocioException.class)
    public void deveLancarErroAoValidarEmailQuandoExistirEmailCadastrado() {
        //cenario
        Mockito.when(usuarioRepository.existsByEmail(Mockito.anyString())).thenReturn(true);

        //acao
        usuarioService.validarEmail("usuario@email.com");

    }


}
