package br.com.docemed.exception;

/**
 * Exceção lançada quando um recurso não é encontrado no sistema.
 */
public class RecursoNaoEncontradoException extends RuntimeException {

    public RecursoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
