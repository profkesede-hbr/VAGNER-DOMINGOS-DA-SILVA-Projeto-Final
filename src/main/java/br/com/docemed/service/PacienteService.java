package br.com.docemed.service;

import br.com.docemed.dto.PacienteRequestDTO;
import br.com.docemed.dto.PacienteResponseDTO;
import br.com.docemed.exception.RecursoNaoEncontradoException;
import br.com.docemed.model.Paciente;
import br.com.docemed.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository repository;

    // ─── CADASTRAR ────────────────────────────────────────────────────────────
    @Transactional
    public PacienteResponseDTO cadastrar(PacienteRequestDTO dto) {
        if (dto.cpf() != null && !dto.cpf().isBlank() && repository.existsByCpf(dto.cpf())) {
            throw new IllegalArgumentException("CPF já cadastrado: " + dto.cpf());
        }
        if (dto.email() != null && !dto.email().isBlank() && repository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("E-mail já cadastrado: " + dto.email());
        }
        Paciente p = new Paciente();
        preencher(p, dto);
        return toDTO(repository.save(p));
    }

    // ─── LISTAR TODOS ATIVOS ──────────────────────────────────────────────────
    public List<PacienteResponseDTO> listarAtivos() {
        return repository.findByAtivoTrue()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // ─── BUSCAR POR ID ────────────────────────────────────────────────────────
    public PacienteResponseDTO buscarPorId(Long id) {
        return toDTO(buscarEntidade(id));
    }

    // ─── BUSCAR POR NOME ──────────────────────────────────────────────────────
    public List<PacienteResponseDTO> buscarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // ─── ATUALIZAR ────────────────────────────────────────────────────────────
    @Transactional
    public PacienteResponseDTO atualizar(Long id, PacienteRequestDTO dto) {
        Paciente p = buscarEntidade(id);
        preencher(p, dto);
        return toDTO(repository.save(p));
    }

    // ─── INATIVAR (soft delete) ───────────────────────────────────────────────
    @Transactional
    public void inativar(Long id) {
        Paciente p = buscarEntidade(id);
        p.setAtivo(false);
        repository.save(p);
    }

    // ─── UTILITÁRIOS ──────────────────────────────────────────────────────────

    public Paciente buscarEntidade(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Paciente não encontrado. ID: " + id));
    }

    private void preencher(Paciente p, PacienteRequestDTO dto) {
        p.setNome(dto.nome());
        p.setDataNascimento(dto.dataNascimento());
        p.setCpf(dto.cpf());
        p.setRg(dto.rg());
        p.setSexo(dto.sexo());
        p.setProfissao(dto.profissao());
        p.setIndicadoPor(dto.indicadoPor());
        p.setTelefone(dto.telefone());
        p.setCelularWhatsapp(dto.celularWhatsapp());
        p.setEmail(dto.email());
        p.setEndereco(dto.endereco());
        p.setBairro(dto.bairro());
        p.setCidade(dto.cidade());
        p.setCep(dto.cep());
        p.setConvenio(dto.convenio());
    }

    private PacienteResponseDTO toDTO(Paciente p) {
        return new PacienteResponseDTO(
                p.getId(),
                p.getNome(),
                p.getDataNascimento(),
                p.getCpf(),
                p.getRg(),
                p.getSexo(),
                p.getProfissao(),
                p.getIndicadoPor(),
                p.getTelefone(),
                p.getCelularWhatsapp(),
                p.getEmail(),
                p.getEndereco(),
                p.getBairro(),
                p.getCidade(),
                p.getCep(),
                p.getConvenio(),
                p.getDataCadastro(),
                p.getAtivo()
        );
    }
}
