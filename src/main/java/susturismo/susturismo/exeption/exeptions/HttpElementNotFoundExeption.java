package susturismo.susturismo.exeption.exeptions;

public class HttpElementNotFoundExeption  extends RuntimeException{

    public HttpElementNotFoundExeption() {
        super();
    }

    public HttpElementNotFoundExeption(String message, Throwable cause){
        super(message, cause);
    }

    public HttpElementNotFoundExeption(String message){
        super(message);
    }

    public HttpElementNotFoundExeption(Throwable cause){
        super(cause);
    }
}
