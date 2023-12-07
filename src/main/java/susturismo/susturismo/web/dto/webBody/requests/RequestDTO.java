package susturismo.susturismo.web.dto.webBody.requests;

import java.util.Map;

public class RequestDTO<T> {

    private Map<String, Object> meta;

    private T request;

    public Map<String, Object> getMeta() {
        return meta;
    }

    public void setMeta(Map<String, Object> meta) {
        this.meta = meta;
    }

    public T getRequest() {
        return request;
    }

    public void setRequest(T request) {
        this.request = request;
    }
}
