package br.com.docemed.repository;

import br.com.docemed.model.PerguntaAnamnese;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerguntaAnamneseRepository extends JpaRepository<PerguntaAnamnese, Long> {

    // Busca somente perguntas ativas ordenadas por posição
    List<PerguntaAnamnese> findByAtivaOrderByOrdemExibicaoAsc(Boolean ativa);
}
