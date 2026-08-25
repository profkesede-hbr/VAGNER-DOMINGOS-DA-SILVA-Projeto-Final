package br.com.docemed.repository;

import br.com.docemed.model.Anamnese;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnamneseRepository extends JpaRepository<Anamnese, Long> {

    // Busca todas as anamneses de um paciente
    List<Anamnese> findAllByPacienteIdOrderByDataPreenchimentoDesc(Long pacienteId);

    // Busca a anamnese mais recente de um paciente
    Optional<Anamnese> findTopByPacienteIdOrderByDataPreenchimentoDesc(Long pacienteId);

    // Verifica se paciente já tem anamnese
    boolean existsByPacienteId(Long pacienteId);
}
