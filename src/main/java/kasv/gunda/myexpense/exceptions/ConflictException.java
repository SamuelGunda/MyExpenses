package kasv.gunda.myexpense.exceptions;

public class ConflictException extends RuntimeException {
    public ConflictException(String entityName) {
        super(entityName + " is already in use. Please use a different " + entityName + ".");
    }
}

