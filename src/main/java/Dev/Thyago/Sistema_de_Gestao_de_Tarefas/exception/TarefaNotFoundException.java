package Dev.Thyago.Sistema_de_Gestao_de_Tarefas.exception;

// Exceção de domínio: lançada quando a tarefa não é encontrada
// Estende RuntimeException (unchecked) → não precisa declarar no throws
public class TarefaNotFoundException extends RuntimeException{

    public TarefaNotFoundException(String message) {
        super(message);
    }
}
