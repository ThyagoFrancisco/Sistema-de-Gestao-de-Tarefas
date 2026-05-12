package Dev.Thyago.Sistema_de_Gestao_de_Tarefas.dto;

import Dev.Thyago.Sistema_de_Gestao_de_Tarefas.model.Tarefa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// DTO de saída: o que a API retorna ao cliente
// Inclui campos calculados/internos como id, createdAt, updatedAt

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarefaResponseDTO {

    private Long id;
    private String title;
    private String description;
    private Boolean completed;
    private Tarefa.Priority priority;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    // Método de mapeamento estático: converte Entity → DTO
    // Evita bibliotecas extras como MapStruct para projetos simples
    public static TarefaResponseDTO fromEntity(Tarefa tarefa) {
        return TarefaResponseDTO.builder()
                .id(tarefa.getId())
                .title(tarefa.getTitle())
                .description(tarefa.getDescription())
                .completed(tarefa.getCompleted())
                .priority(tarefa.getPriority())
                .createdAt(tarefa.getCreatedAt())
                .updatedAt(tarefa.getUpdatedAt())
                .build();
    }
}
