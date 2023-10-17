package dev.matheushbmelo.project.domain.exceptions;

public class ValidationException extends RuntimeException {
    public ValidationException(String mensagem) {
        super(mensagem);
    }
}
