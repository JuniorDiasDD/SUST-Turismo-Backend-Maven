package susturismo.susturismo.web.api;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import susturismo.susturismo.web.dto.DocumentDTO;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTO;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTOList;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTO;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTOList;

import java.util.UUID;

@RequestMapping("api/v1/document")
public interface DocumentApi {

    @GetMapping(path = "")
    public ResponseEntity<ResponseDTOList<DocumentDTO>> findAll(@RequestBody(required = false) RequestDTOList<DocumentDTO> request);
    @GetMapping(path = "/my")
    public ResponseEntity<ResponseDTOList<DocumentDTO>> findAllByUser(@RequestBody(required = false) RequestDTOList<DocumentDTO> request);
    @GetMapping(path = "/{id}")
    ResponseEntity<ResponseDTO<DocumentDTO>> findByID(
            @PathVariable(value = "id") UUID id,
            @RequestBody(required = false) RequestDTO<DocumentDTO> request);
    @PostMapping(path = "")
    ResponseEntity<ResponseDTO<DocumentDTO>> insert(@RequestBody(required = false) @Valid RequestDTO<DocumentDTO> request);
    @PutMapping(path = "")
    ResponseEntity<ResponseDTO<DocumentDTO>> edit(@RequestBody(required = false) @Valid RequestDTO<DocumentDTO> request);
    @DeleteMapping(path = "/{id}")
    ResponseEntity<ResponseDTO<DocumentDTO>> delete(
            @PathVariable(value = "id") UUID id,
            @RequestBody(required = false) RequestDTO<DocumentDTO> request);

}
