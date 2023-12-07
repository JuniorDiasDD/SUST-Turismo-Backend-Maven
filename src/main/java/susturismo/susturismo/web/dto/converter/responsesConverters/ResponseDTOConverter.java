package susturismo.susturismo.web.dto.converter.responsesConverters;

import susturismo.susturismo.web.dto.webBody.requests.RequestDTO;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTOList;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTO;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTOList;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ResponseDTOConverter<T> {

    public ResponseDTOList<T> createResponseWithList(RequestDTOList<T> request, List<T> result, String title, Boolean ok) {

        ResponseDTOList<T> response = new ResponseDTOList<T>();

        Map<String, Object> meta = new HashMap<String, Object>();

        if (request != null) {

            if (request.getMeta() != null) {

                meta = request.getMeta();

            }

        }

        List<HashMap<String, Object>> requestMessages = new ArrayList<>();

        if (meta.containsKey("messages")) {

            requestMessages = (List<HashMap<String, Object>>) meta.get("messages");

        }

        requestMessages.forEach(map -> map.put("title", title));

        meta.replace("messages", requestMessages);

        meta.put("ok", true);

        response.setMeta(meta);

        response.setResponse(result);

        return response;
    }

    public ResponseDTO<T> createResponse(RequestDTO<T> request, T result, String title, Boolean ok) {

        ResponseDTO<T> response = new ResponseDTO<>();

        Map<String, Object> meta = new HashMap<>();

        if (request != null) {

            if (request.getMeta() != null) {

                meta = request.getMeta();

            }

        }

        List<HashMap<String, Object>> requestMessages = new ArrayList<>();

        if (meta.containsKey("messages")) {

            requestMessages = (List<HashMap<String, Object>>) meta.get("messages");

        }

        requestMessages.forEach(map -> map.put("title", title));

        meta.replace("messages", requestMessages);

        meta.put("ok", ok);

        response.setMeta(meta);

        response.setResponse(result);

        return response;

    }

    public ResponseDTO<Exception> createResponseWithException(Exception ex, HttpStatus code, String title) {

        ResponseDTO<Exception> response = new ResponseDTO<>();

        Map<String, Object> meta = new HashMap<>();

        HashMap<String, Object> messages = new HashMap<>();

        messages.put("code", code);

        messages.put("title", title);

        messages.put("type", "error");

        List<HashMap<String, Object>> requestMessages = new ArrayList<>();

        requestMessages.add(messages);

        meta.put("messages", requestMessages);

        meta.put("ok", false);

        response.setMeta(meta);

        return response;
    }



}
