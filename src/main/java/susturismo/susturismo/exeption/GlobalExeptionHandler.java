package susturismo.susturismo.exeption;

import susturismo.susturismo.exeption.exeptions.*;
import susturismo.susturismo.web.dto.converter.responsesConverters.ResponseDTOConverter;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.lang.IllegalArgumentException;


@RestControllerAdvice
public class GlobalExeptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private ResponseDTOConverter<Exception> responseDTOConverter;


    @ExceptionHandler(HttpUnauthorizedExeption.class)
    public ResponseEntity<Object> handleHttpUnauthorizedException(HttpUnauthorizedExeption ex) {

        ResponseDTO<Exception> response;

        HttpHeaders headers = new HttpHeaders();

        HttpStatus status = HttpStatus.UNAUTHORIZED;

        response = responseDTOConverter.createResponseWithException(ex, status, ex.getMessage());

        return new ResponseEntity<Object>(response, headers, status);

    }
    @ExceptionHandler(HttpNotPermissionExeption.class)
    public ResponseEntity<Object> handleHttpNotPermissionException(HttpNotPermissionExeption ex) {

        ResponseDTO<Exception> response;

        HttpHeaders headers = new HttpHeaders();

        HttpStatus status = HttpStatus.FORBIDDEN;

        response = responseDTOConverter.createResponseWithException(ex, status, ex.getMessage());

        return new ResponseEntity<Object>(response, headers, status);

    }

    @ExceptionHandler(HttpUpdateFailedException.class)
    public ResponseEntity<Object> handleHttpUpdateFailedException(HttpUpdateFailedException ex) {

        ResponseDTO<Exception> response;

        HttpHeaders headers = new HttpHeaders();

        HttpStatus status = HttpStatus.BAD_REQUEST;

        response = responseDTOConverter.createResponseWithException(ex, status, ex.getMessage());

        return new ResponseEntity<Object>(response, headers, status);

    }

    @ExceptionHandler(HttpInsertFailedException.class)
    public ResponseEntity<Object> handleHttpInsertFailedException(HttpInsertFailedException ex) {

        ResponseDTO<Exception> response;

        HttpHeaders headers = new HttpHeaders();

        HttpStatus status = HttpStatus.BAD_REQUEST;

        response = responseDTOConverter.createResponseWithException(ex, status, ex.getMessage());

        return new ResponseEntity<Object>(response, headers, status);

    }

    @ExceptionHandler(HttpElementNotFoundExeption.class)
    public ResponseEntity<Object> handleHttpNoElementFound(HttpElementNotFoundExeption ex) {

        ResponseDTO<Exception> response;

        HttpHeaders headers = new HttpHeaders();

        HttpStatus status = HttpStatus.BAD_REQUEST;

        response = responseDTOConverter.createResponseWithException(ex, status, ex.getMessage());

        return new ResponseEntity<Object>(response, headers, status);

    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> illegalArgumentException(IllegalArgumentException ex) {

        ResponseDTO<Exception> response;

        HttpHeaders headers = new HttpHeaders();

        HttpStatus status = HttpStatus.BAD_REQUEST;

        response = responseDTOConverter.createResponseWithException(ex, status, ex.getMessage());

        return new ResponseEntity<Object>(response, headers, status);

    }


}

