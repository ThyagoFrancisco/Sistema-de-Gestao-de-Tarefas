package Dev.Thyago.Sistema_de_Gestao_de_Tarefas.service;

import Dev.Thyago.Sistema_de_Gestao_de_Tarefas.dto.TarefaRequestDTO;
import Dev.Thyago.Sistema_de_Gestao_de_Tarefas.dto.TarefaResponseDTO;
import Dev.Thyago.Sistema_de_Gestao_de_Tarefas.model.Tarefa;

import java.util.List;

// Interface de serviço: define o contrato de negócio
public interface TarefaService {

    List<TarefaResponseDTO> findAll();

    TarefaResponseDTO create(TarefaResponseDTO dto);

    TarefaResponseDTO update(Long id, TarefaRequestDTO dto);


    // PATCH semântico: apenas inverte o campo "completed"
    TarefaResponseDTO toggleComplete(long id);

    void delete(Long id);

    List<TarefaResponseDTO> findByCompleted(Boolean completed);

    List<TarefaResponseDTO> findByPriority(Tarefa.Priority priority);

    List<TarefaResponseDTO> search(String title);
}
