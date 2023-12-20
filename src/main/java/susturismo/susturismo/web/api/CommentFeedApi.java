package susturismo.susturismo.web.api;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import susturismo.susturismo.web.dto.CommentFeedDTO;
import susturismo.susturismo.web.dto.LikeDTO;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTO;

import java.util.UUID;

@RequestMapping("api/v1/comment")
public interface CommentFeedApi {
    @PostMapping(path = "")
    ResponseEntity<Object> insert(@RequestBody(required = false) @Valid RequestDTO<CommentFeedDTO> request);

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id,
                                  @RequestBody(required = false) RequestDTO<CommentFeedDTO> request);

}
