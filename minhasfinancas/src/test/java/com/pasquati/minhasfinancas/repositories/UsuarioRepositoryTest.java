package com.pasquati.minhasfinancas.repositories;

import com.pasquati.minhasfinancas.model.entity.Usuario;
import org.assertj.core.api.Assertions;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@DataJpaTest //Cria uma instancia do banco de dados na memoria
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    public void deveVerificarExistenciaDeUmEmail() {

        //cenario
        Usuario usuario = new Usuario();
        usuario.setNome("usuario");
        usuario.setEmail("usuario@email.com");
        testEntityManager.persist(usuario);

        //Ação ou execução
        boolean teste = usuarioRepository.existsByEmail("usuario@email.com");

        //Verificação
        Assertions.assertThat(teste).isTrue();
    }

    @Test
    public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComEmail() {
        //Cenario

        //açao
        boolean teste = usuarioRepository.existsByEmail("usuario@email.com");

        //Verificacao
        Assertions.assertThat(teste).isFalse();
    }

    @Test
    public void devePersistirUmUsuarioNaBaseDeDados() {
        //cenario
        Usuario usuario = new Usuario();
        usuario.setNome("usuario");
        usuario.setEmail("usuario@gmail.com");
        usuario.setSenha("senha");

        //acao
        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        //Verificacao
        Assertions.assertThat(usuario.getId()).isNotNull();

    }

    @Test
    public void deveBuscarUmUsuarioPorEmail(){
        //cenario
        Usuario usuario = new Usuario();
        usuario.setNome("usuario");
        usuario.setEmail("usuario@gmail.com");
        usuario.setSenha("usuario");
        testEntityManager.persist(usuario);

        //acao
       Optional<Usuario> result =  usuarioRepository.findByEmail("usuario@gmail.com");

        //Verificacao
        Assertions.assertThat(result.isPresent()).isTrue();

    }

    @Test
    public void deveRetornarVazioAoBuscarUsuarioNaBaseDeDados(){
        //acao
        Optional<Usuario> result = usuarioRepository.findByEmail("usuario@gmail.com");

        //Validacao
        Assertions.assertThat(result.isPresent()).isFalse();
    }

}
