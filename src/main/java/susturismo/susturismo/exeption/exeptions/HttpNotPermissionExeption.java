package susturismo.susturismo.exeption.exeptions;

public class HttpNotPermissionExeption extends RuntimeException{

    public HttpNotPermissionExeption() {
        super();
    }

    public HttpNotPermissionExeption(String message, Throwable cause){
        super(message, cause);
    }

    public HttpNotPermissionExeption(String message){
        super(message);
    }

    public HttpNotPermissionExeption(Throwable cause){
        super(cause);
    }
}
