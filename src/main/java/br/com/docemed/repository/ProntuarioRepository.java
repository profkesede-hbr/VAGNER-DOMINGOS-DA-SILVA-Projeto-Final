package br.com.docemed.repository;

import br.com.docemed.model.ProntuarioAtendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProntuarioRepository extends JpaRepository<ProntuarioAtendimento, Long> {
    List<ProntuarioAtendimento> findByPacienteIdOrderByDataAtendimentoDesc(Long pacienteId);
    Optional<ProntuarioAtendimento> findTopByPacienteIdOrderByDataAtendimentoDesc(Long pacienteId);
}
