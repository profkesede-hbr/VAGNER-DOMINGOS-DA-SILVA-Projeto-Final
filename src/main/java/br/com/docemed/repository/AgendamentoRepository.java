package br.com.docemed.repository;

import br.com.docemed.model.Agendamento;
import br.com.docemed.model.StatusAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    List<Agendamento> findByPacienteIdOrderByDataHoraDesc(Long pacienteId);
    List<Agendamento> findByStatusOrderByDataHoraAsc(StatusAgendamento status);
    List<Agendamento> findByDataHoraBetweenOrderByDataHoraAsc(LocalDateTime inicio, LocalDateTime fim);
    Optional<Agendamento> findTopByPacienteIdOrderByDataHoraDesc(Long pacienteId);
}
