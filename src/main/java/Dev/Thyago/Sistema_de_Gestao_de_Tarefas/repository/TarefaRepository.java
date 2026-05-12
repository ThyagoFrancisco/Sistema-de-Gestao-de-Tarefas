package Dev.Thyago.Sistema_de_Gestao_de_Tarefas.repository;

import Dev.Thyago.Sistema_de_Gestao_de_Tarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    // JpaRepository<Task, Long> fornece gratuitamente:
    //   save(), findById(), findAll(), deleteById(), existsById().
    List<Tarefa> findByCompleted(Boolean completed);

    List<Tarefa> findByPriority(Tarefa.Priority priority);

    List<Tarefa> findByTitleContainingIgnoreCase(String title);

    // Consulta JPQL manual para ordenar tarefas pendentes por prioridade
    @Query("SELECT t FROM Task t WHERE t.completed = false ORDER BY t.priority DESC, t.createdAt ASC")
    List<Tarefa> findPendingTasksSorted();

    long countByCompleted(Boolean completed);
}
