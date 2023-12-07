package susturismo.susturismo.exeption.exeptions;

public class HttpUpdateFailedException extends RuntimeException{

    public HttpUpdateFailedException() {
        super();
    }

    public HttpUpdateFailedException(String message) {
        super(message);
    }

    public HttpUpdateFailedException(String message, Throwable cause){
        super(message, cause);
    }
}
