package br.com.docemed.repository;

import br.com.docemed.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    // Busca por nome (parcial, ignora maiúsculas/minúsculas)
    List<Paciente> findByNomeContainingIgnoreCase(String nome);

    // Busca por CPF
    Optional<Paciente> findByCpf(String cpf);

    // Busca por e-mail
    Optional<Paciente> findByEmail(String email);

    // Lista apenas pacientes ativos
    List<Paciente> findByAtivoTrue();

    // Verifica se CPF já existe
    boolean existsByCpf(String cpf);

    // Verifica se e-mail já existe
    boolean existsByEmail(String email);
}
