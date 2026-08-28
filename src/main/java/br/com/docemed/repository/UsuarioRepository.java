package br.com.docemed.repository;

import br.com.docemed.model.PerfilUsuario;
import br.com.docemed.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByLogin(String login);
    Optional<Usuario> findByLoginAndSenha(String login, String senha);
    Optional<Usuario> findByPacienteId(Long pacienteId);
    List<Usuario> findByPerfil(PerfilUsuario perfil);
    boolean existsByLogin(String login);
}
