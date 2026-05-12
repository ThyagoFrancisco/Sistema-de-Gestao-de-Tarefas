package Dev.Thyago.Sistema_de_Gestao_de_Tarefas.dto;

import Dev.Thyago.Sistema_de_Gestao_de_Tarefas.model.Tarefa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarefaRequestDTO {

    @NotBlank(message = "Título é obrigatório")
    @Size(min = 1, max = 100, message = "Título deve ter entre 1 e 100 caracteres")
    private String title;

    @Size(max = 500, message = "Descrição não pode ultrapassar 500 caracteres")
    private String description;

    private Boolean completed = false;

    private Tarefa.Priority priority = Tarefa.Priority.MEDIUM;

}
