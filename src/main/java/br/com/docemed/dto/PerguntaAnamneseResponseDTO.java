package br.com.docemed.dto;

import br.com.docemed.model.PerguntaAnamnese;

/**
 * DTO de resposta para PerguntaAnamnese.
 */
public record PerguntaAnamneseResponseDTO(
        Long id,
        String enunciado,
        PerguntaAnamnese.TipoResposta tipoResposta,
        Boolean ativa,
        Integer ordemExibicao
) {}
