package susturismo.susturismo.web.api;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import susturismo.susturismo.web.dto.EventDTO;
import susturismo.susturismo.web.dto.LikeDTO;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTO;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTOList;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTO;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTOList;

import java.util.UUID;

@RequestMapping("api/v1/like")
public interface LikeFeedApi {
    @PostMapping(path = "")
    ResponseEntity<Object> insert(@RequestBody(required = false) @Valid RequestDTO<LikeDTO> request);

    @DeleteMapping(path = "")
    ResponseEntity<Object> delete(@RequestBody(required = false) RequestDTO<LikeDTO> request);

}
