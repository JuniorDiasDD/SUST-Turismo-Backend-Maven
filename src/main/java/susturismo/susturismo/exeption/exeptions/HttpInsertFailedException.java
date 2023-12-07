package susturismo.susturismo.exeption.exeptions;

public class HttpInsertFailedException extends RuntimeException{

    public HttpInsertFailedException() {
        super();
    }

    public HttpInsertFailedException(String message) {
        super(message);
    }

    public HttpInsertFailedException(String message, Throwable cause){
        super(message, cause);
    }
}
