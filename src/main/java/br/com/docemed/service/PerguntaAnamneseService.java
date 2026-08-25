package br.com.docemed.service;

import br.com.docemed.dto.PerguntaAnamneseRequestDTO;
import br.com.docemed.dto.PerguntaAnamneseResponseDTO;
import br.com.docemed.exception.RecursoNaoEncontradoException;
import br.com.docemed.model.PerguntaAnamnese;
import br.com.docemed.repository.PerguntaAnamneseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PerguntaAnamneseService {

    private final PerguntaAnamneseRepository repository;

    // ─── LISTAR TODAS AS PERGUNTAS ATIVAS ────────────────────────────────────
    public List<PerguntaAnamneseResponseDTO> listarAtivas() {
        return repository.findByAtivaOrderByOrdemExibicaoAsc(true)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // ─── LISTAR TODAS (inclusive inativas — para o admin) ─────────────────────
    public List<PerguntaAnamneseResponseDTO> listarTodas() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // ─── BUSCAR POR ID ────────────────────────────────────────────────────────
    public PerguntaAnamneseResponseDTO buscarPorId(Long id) {
        return toDTO(buscarEntidade(id));
    }

    // ─── CRIAR PERGUNTA ───────────────────────────────────────────────────────
    public PerguntaAnamneseResponseDTO criar(PerguntaAnamneseRequestDTO dto) {
        PerguntaAnamnese pergunta = new PerguntaAnamnese();
        pergunta.setEnunciado(dto.enunciado());
        pergunta.setTipoResposta(dto.tipoResposta());
        pergunta.setAtiva(dto.ativa() != null ? dto.ativa() : true);
        pergunta.setOrdemExibicao(dto.ordemExibicao() != null ? dto.ordemExibicao() : 0);
        return toDTO(repository.save(pergunta));
    }

    // ─── ATUALIZAR PERGUNTA ───────────────────────────────────────────────────
    public PerguntaAnamneseResponseDTO atualizar(Long id, PerguntaAnamneseRequestDTO dto) {
        PerguntaAnamnese pergunta = buscarEntidade(id);
        pergunta.setEnunciado(dto.enunciado());
        pergunta.setTipoResposta(dto.tipoResposta());
        if (dto.ativa() != null) pergunta.setAtiva(dto.ativa());
        if (dto.ordemExibicao() != null) pergunta.setOrdemExibicao(dto.ordemExibicao());
        return toDTO(repository.save(pergunta));
    }

    // ─── ATIVAR / DESATIVAR PERGUNTA ──────────────────────────────────────────
    public PerguntaAnamneseResponseDTO alterarStatus(Long id, boolean ativa) {
        PerguntaAnamnese pergunta = buscarEntidade(id);
        pergunta.setAtiva(ativa);
        return toDTO(repository.save(pergunta));
    }

    // ─── EXCLUIR PERGUNTA ─────────────────────────────────────────────────────
    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Pergunta de anamnese não encontrada. ID: " + id);
        }
        repository.deleteById(id);
    }

    // ─── UTILITÁRIOS ──────────────────────────────────────────────────────────

    public PerguntaAnamnese buscarEntidade(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Pergunta de anamnese não encontrada. ID: " + id));
    }

    private PerguntaAnamneseResponseDTO toDTO(PerguntaAnamnese p) {
        return new PerguntaAnamneseResponseDTO(
                p.getId(),
                p.getEnunciado(),
                p.getTipoResposta(),
                p.getAtiva(),
                p.getOrdemExibicao()
        );
    }
}
