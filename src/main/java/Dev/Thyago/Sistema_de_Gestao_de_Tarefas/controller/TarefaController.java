package Dev.Thyago.Sistema_de_Gestao_de_Tarefas.controller;

import Dev.Thyago.Sistema_de_Gestao_de_Tarefas.dto.TarefaRequestDTO;
import Dev.Thyago.Sistema_de_Gestao_de_Tarefas.dto.TarefaResponseDTO;
import Dev.Thyago.Sistema_de_Gestao_de_Tarefas.model.Tarefa;
import Dev.Thyago.Sistema_de_Gestao_de_Tarefas.service.TarefaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService;


    // GET /api/tasks                    → todas as tarefas
    // GET /api/tasks?completed=true     → filtro por status
    // GET /api/tasks?priority=HIGH      → filtro por prioridade
    // GET /api/tasks?search=comprar     → busca por título
    @GetMapping
    public ResponseEntity<List<TarefaResponseDTO>> findAll(
            @RequestParam(required = false) Boolean completed,
            @RequestParam(required = false)Tarefa.Priority priority,
            @RequestParam(required = false) String search) {

        if (search != null && !search.isBlank()) {
            return ResponseEntity.ok(tarefaService.search(search));
        }
        if (completed != null) {
            return ResponseEntity.ok(tarefaService.findByCompleted(completed));
        }
        if (priority != null){
            return ResponseEntity.ok(tarefaService.findByPriority(priority));
        }
        return ResponseEntity.ok(tarefaService.findAll());
    }

    // GET /api/tasks/{id}
    @GetMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(tarefaService.findById(id));
    }

    // POST /api/tasks
    @PostMapping
    public ResponseEntity<TarefaResponseDTO> create(@Valid @RequestBody TarefaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.create(dto));
    }

    // PUT /api/tasks/{id}
    @PutMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody TarefaRequestDTO dto) {
        return ResponseEntity.ok(tarefaService.update(id,dto));
    }

    // PATCH /api/tasks/{id}/toggle
    @PatchMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> toggleComplete(@PathVariable Long id) {
        return ResponseEntity.ok(tarefaService.toggleComplete(id));
    }

    // DELETE /api/tasks/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tarefaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
