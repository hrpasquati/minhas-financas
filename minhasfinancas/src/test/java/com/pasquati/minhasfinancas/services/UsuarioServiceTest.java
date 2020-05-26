//package com.pasquati.minhasfinancas.services;
//
//import com.pasquati.minhasfinancas.exception.ErroAutenticacao;
//import com.pasquati.minhasfinancas.exception.RegraNegocioException;
//import com.pasquati.minhasfinancas.model.entity.Usuario;
//import com.pasquati.minhasfinancas.repositories.UsuarioRepository;
//import com.pasquati.minhasfinancas.services.impl.UsuarioServiceImpl;
//import org.assertj.core.api.Assertions;
//import org.junit.Before;
//
//import org.junit.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mockito;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.Optional;
//
//@SpringBootTest
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@ExtendWith(SpringExtension.class)
//@ActiveProfiles("test")
//public class UsuarioServiceTest {
//
//
//    private UsuarioService usuarioService;
//    @MockBean
//    private UsuarioRepository usuarioRepository;
//
//    @Before
//    public void setUp() {
//        usuarioService = new UsuarioServiceImpl(usuarioRepository);
//    }
//
//    @Test(expected = Test.None.class)
//    public void deveAutenticarUmUsuarioComSucesso(){
//        //cenario
//        String email = "usuario@email.com";
//        String senha = "usuario";
//        Long id = 1l;
//
//        Usuario usuario = new Usuario();
//        usuario.setId(id);
//        usuario.setEmail(email);
//        usuario.setSenha(senha);
//
//        Mockito.when(usuarioRepository.findByEmail(email)).thenReturn(Optional.of(usuario));
//
//        //acao
//        Usuario result = usuarioService.autentica(email, senha);
//
//        //verificacao
//
//        Assertions.assertThat(result).isNotNull();
//
//    }
//
//    @Test(expected = ErroAutenticacao.class)
//    public void deveLancarErroQuandoNaoEncontrarUsuarioCadastradoComEmailInformado(){
//        //cenario
//        Mockito.when(usuarioRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
//
//        //acao
//        usuarioService.autentica("usuario@email", "usuario");
//    }
//
//    @Test(expected = Test.None.class)
//    public void deveValidarEmail() {
//        //cenario
//        Mockito.when(usuarioRepository.existsByEmail(Mockito.anyString())).thenReturn(false);
//
//        //acao
//        usuarioService.validarEmail("usuario@email.com");
//    }
//
//    @Test(expected = RegraNegocioException.class)
//    public void deveLancarErroAoValidarEmailQuandoExistirEmailCadastrado() {
//        //cenario
//        Mockito.when(usuarioRepository.existsByEmail(Mockito.anyString())).thenReturn(true);
//
//        //acao
//        usuarioService.validarEmail("usuario@email.com");
//
//    }
//
//
//
//
//}
