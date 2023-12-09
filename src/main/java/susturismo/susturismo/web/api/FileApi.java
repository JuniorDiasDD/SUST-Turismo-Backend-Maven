package susturismo.susturismo.web.api;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import susturismo.susturismo.web.dto.FileDTO;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTO;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTO;

@RequestMapping("api/v1/file")
public interface FileApi {

    @PostMapping(path = "")
    ResponseEntity<ResponseDTO<FileDTO>> insert(@RequestBody(required = false) @Valid RequestDTO<FileDTO> request);

}
