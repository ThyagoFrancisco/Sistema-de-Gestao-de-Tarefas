package Dev.Thyago.Sistema_de_Gestao_de_Tarefas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.http.parser.Priority;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_tarefa")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Título é obrigatório")
    @Size(min = 1, max = 300, message = "Título deve ter entre 1 e 100 caracteres")
    @Column(nullable = false)
    private String title;

    @Size(max = 500, message = "Descrição não pode ultrapassar 500 caracteres")
    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private Boolean completed = false;

    @Enumerated(EnumType.STRING)            // Persiste "LOW", "MEDIUM", "HIGH" (não 0, 1, 2)
    @Column(nullable = false)
    private Priority priority = Priority.MEDIUM;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    // Callback JPA: executado automaticamente antes de INSERT
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }


    // Callback JPA: executado automaticamente antes de UPDATE
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum Priority {
        LOW, MEDIUM, HIGH
    }
}
