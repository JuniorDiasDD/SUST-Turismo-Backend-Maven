package susturismo.susturismo.web.dto.webBody.responses;

import java.util.Map;

public class ResponseDTO<T> {

    private Map<String, Object> meta;

    private T response;

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public Map<String, Object> getMeta() {
        return meta;
    }

    public void setMeta(Map<String, Object> meta) {
        this.meta = meta;
    }
}
