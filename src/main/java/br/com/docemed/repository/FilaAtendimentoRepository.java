package br.com.docemed.repository;

import br.com.docemed.model.FilaAtendimento;
import br.com.docemed.model.StatusFila;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilaAtendimentoRepository extends JpaRepository<FilaAtendimento, Long> {
    List<FilaAtendimento> findByStatusOrderByPosicaoAsc(StatusFila status);
    List<FilaAtendimento> findByStatusInOrderByPosicaoAsc(List<StatusFila> statuses);
    Optional<FilaAtendimento> findTopByPacienteIdAndStatusInOrderByHorarioEntradaDesc(Long pacienteId, List<StatusFila> statuses);
    Optional<FilaAtendimento> findTopByStatusOrderByHorarioChamadaDesc(StatusFila status);
    Optional<FilaAtendimento> findByPacienteIdAndStatus(Long pacienteId, StatusFila status);
    boolean existsByPacienteIdAndStatusIn(Long pacienteId, List<StatusFila> statuses);

    @org.springframework.data.jpa.repository.Query("SELECT MAX(f.posicao) FROM FilaAtendimento f")
    Integer findMaxPosicao();
}
