package susturismo.susturismo.exeption.exeptions;

public class HttpUnauthorizedExeption extends RuntimeException{

    public HttpUnauthorizedExeption() {
        super();
    }

    public HttpUnauthorizedExeption(String message, Throwable cause){
        super(message, cause);
    }

    public HttpUnauthorizedExeption(String message){
        super(message);
    }

    public HttpUnauthorizedExeption(Throwable cause){
        super(cause);
    }
}
