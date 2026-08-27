package br.com.docemed.dto;

import br.com.docemed.model.PerguntaAnamnese;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO para criação e atualização de uma PerguntaAnamnese.
 */
public record PerguntaAnamneseRequestDTO(

        @NotBlank(message = "O enunciado da pergunta não pode ser vazio.")
        String enunciado,

        @NotNull(message = "O tipo de resposta é obrigatório.")
        PerguntaAnamnese.TipoResposta tipoResposta,

        Boolean ativa,

        Integer ordemExibicao
) {}
