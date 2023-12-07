package susturismo.susturismo.web.dto.webBody.requests;

import java.util.List;
import java.util.Map;

public class RequestDTOList<T> {

    private Map<String, Object> meta;

    private List<T> request;

    public Map<String, Object> getMeta() {
        return meta;
    }

    public void setMeta(Map<String, Object> meta) {
        this.meta = meta;
    }

    public List<T> getRequest() {
        return request;
    }

    public void setRequest(List<T> request) {
        this.request = request;
    }
}
