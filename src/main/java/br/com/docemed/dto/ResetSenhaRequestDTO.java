package br.com.docemed.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO para Redefinição ou Troca de Senha pela Recepção")
public class ResetSenhaRequestDTO {

    @NotBlank(message = "A nova senha ou confirmação de reset é obrigatória.")
    @Schema(description = "Nova senha provisória ou definitiva", example = "Mudar@123")
    private String novaSenha;

    @Schema(description = "Enviar e-mail com a nova senha para o paciente?", example = "true")
    @Builder.Default
    private Boolean enviarEmail = true;
}
