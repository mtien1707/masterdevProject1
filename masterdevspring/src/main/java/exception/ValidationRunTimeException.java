package exception;

import javax.xml.bind.ValidationException;

public class ValidationRunTimeException extends ValidationException {
    public ValidationRunTimeException(String message) {
        super(message);
    }
}


