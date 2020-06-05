package viktor.home.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ForbiddenActionException extends Exception {
    public ForbiddenActionException() {
        super();
    }
    public ForbiddenActionException(String message) {
        super(message);
    }

    public ForbiddenActionException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
