package susturismo.susturismo.exeption.exeptions;

public class IllegalArgumentException extends RuntimeException{

    public IllegalArgumentException() {
        super();
    }

    public IllegalArgumentException(String message) {
        super(message);
    }

    public IllegalArgumentException(String message, Throwable cause){
        super(message, cause);
    }
}
