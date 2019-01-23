package Model.Exception;

public class FileAlreadyOpenedException extends Exception {
    public FileAlreadyOpenedException(final String errorMessage) {
        super(errorMessage);
    }
}

