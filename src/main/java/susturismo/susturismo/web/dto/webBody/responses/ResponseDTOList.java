package susturismo.susturismo.web.dto.webBody.responses;


import java.util.List;
import java.util.Map;

public class ResponseDTOList<T> {

    private Map<String, Object> meta;

    private List<T> response;

    public Map<String, Object> getMeta() {
        return meta;
    }

    public void setMeta(Map<String, Object> meta) {
        this.meta = meta;
    }

    public List<T> getResponse() {
        return response;
    }

    public void setResponse(List<T> response) {
        this.response = response;
    }
}
