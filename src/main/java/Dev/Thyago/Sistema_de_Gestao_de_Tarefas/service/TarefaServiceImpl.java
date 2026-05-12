package Dev.Thyago.Sistema_de_Gestao_de_Tarefas.service;

import Dev.Thyago.Sistema_de_Gestao_de_Tarefas.dto.TarefaRequestDTO;
import Dev.Thyago.Sistema_de_Gestao_de_Tarefas.dto.TarefaResponseDTO;
import Dev.Thyago.Sistema_de_Gestao_de_Tarefas.exception.TarefaNotFoundException;
import Dev.Thyago.Sistema_de_Gestao_de_Tarefas.model.Tarefa;
import Dev.Thyago.Sistema_de_Gestao_de_Tarefas.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TarefaServiceImpl implements TarefaService{

    private TarefaRepository tarefaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TarefaResponseDTO> findAll() {
        return tarefaRepository.findAll()
                .stream()
                .map(TarefaResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public TarefaResponseDTO findById(Long id) {
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElse(() -> new TarefaNotFoundException("Tarefa não encontrada com id:" +id ));
        return TarefaResponseDTO.fromEntity(tarefa);
    }

    @Override
    public TarefaResponseDTO create(TarefaResponseDTO dto) {
        Tarefa tarefa = Tarefa.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .completed(dto.getCompleted() != null ? dto.getCompleted() : false)
                .priority(dto.getPriority() != null ? dto.getPriority() : Tarefa.Priority.MEDIUM)
                .build();
        return TarefaResponseDTO.fromEntity(tarefaRepository.save(tarefa));
    }

    @Override
    public TarefaResponseDTO update(Long id, TarefaRequestDTO dto) {
        // Busca a entidade gerenciada pelo JPA (dentro da transação)
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new TarefaNotFoundException("Tarefa não encontrada com id: " +id));

        // Atualiza os campos (o save() fará UPDATE pois a entidade já tem ID)
        tarefa.setTitle(dto.getTitle());
        tarefa.setDescription(dto.getDescription());
        tarefa.setCompleted(dto.getCompleted());
        tarefa.setPriority(dto.getPriority());

        return TarefaResponseDTO.fromEntity(tarefaRepository.save(tarefa));
    }

    @Override
    public TarefaResponseDTO toggleComplete(Long id) {
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new TarefaNotFoundException("Tarefa não encontrada com id: " +id));

        // Inverte o estado: true → false, false → true
        tarefa.setCompleted(!tarefa.getCompleted());
        return TarefaResponseDTO.fromEntity(tarefaRepository.save(tarefa));
    }

    @Override
    public void delete(Long id) {
        if (!tarefaRepository.existsById(id)) {
            throw new TarefaNotFoundException("Tarefa não encontrada com id:" +id);
        }
        tarefaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TarefaRequestDTO> findByCompleted(Boolean completed) {
        return tarefaRepository.findByCompleted(completed)
                .stream()
                .map(TarefaResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TarefaResponseDTO> search(String title) {
        return tarefaRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(TarefaResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
