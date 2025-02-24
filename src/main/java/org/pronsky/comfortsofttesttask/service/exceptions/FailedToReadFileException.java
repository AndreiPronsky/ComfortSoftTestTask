package org.pronsky.comfortsofttesttask.service.exceptions;

public class FailedToReadFileException extends RuntimeException {

    public static final String FILE_NOT_FOUND = "Failed to read file";

    public FailedToReadFileException() {
        super(FILE_NOT_FOUND);
    }
}
