package susturismo.susturismo.web.api;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import susturismo.susturismo.web.dto.EventDTO;
import susturismo.susturismo.web.dto.NoticiaDTO;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTO;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTOList;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTO;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTOList;

import java.util.UUID;

@RequestMapping("api/v1/noticia")
public interface NoticiaApi {

    @GetMapping(path = "/all")
    public ResponseEntity<ResponseDTOList<NoticiaDTO>> findAll(@RequestBody(required = false) RequestDTOList<NoticiaDTO> request);
    @GetMapping(path = "/limit")
    public ResponseEntity<ResponseDTOList<NoticiaDTO>> findAllLimit(@RequestBody(required = false) RequestDTOList<NoticiaDTO> request);
    @GetMapping(path = "/my")
    public ResponseEntity<ResponseDTOList<NoticiaDTO>> findAllByUser(@RequestBody(required = false) RequestDTOList<NoticiaDTO> request);
    @GetMapping(path = "")
    public ResponseEntity<ResponseDTOList<NoticiaDTO>> findAllActive(@RequestBody(required = false) RequestDTOList<NoticiaDTO> request);
    @GetMapping(path = "/aprove")
    public ResponseEntity<ResponseDTOList<NoticiaDTO>> findAllApprove(@RequestBody(required = false) RequestDTOList<NoticiaDTO> request);
    @GetMapping(path = "/{id}")
    ResponseEntity<ResponseDTO<NoticiaDTO>> findByID(
            @PathVariable(value = "id") UUID id,
            @RequestBody(required = false) RequestDTO<NoticiaDTO> request);

    @PostMapping(path = "")
    ResponseEntity<ResponseDTO<NoticiaDTO>> insert(@RequestBody(required = false) @Valid RequestDTO<NoticiaDTO> request);
    @PostMapping(path = "/active")
    ResponseEntity<Object> active(@RequestBody(required = false) RequestDTOList<UUID> request);
    @PostMapping(path = "/approve")
    ResponseEntity<Object> approve(@RequestBody(required = false) RequestDTO<NoticiaDTO> request);
    @PostMapping(path = "/disable")
    ResponseEntity<Object> disable(@RequestBody(required = false) RequestDTO<NoticiaDTO> request);

    @PutMapping(path = "")
    ResponseEntity<ResponseDTO<NoticiaDTO>> update(@RequestBody(required = false) RequestDTO<NoticiaDTO> request);

    @DeleteMapping(path = "/{id}")
    ResponseEntity<ResponseDTO<NoticiaDTO>> delete(
            @PathVariable(value = "id") UUID id,
            @RequestBody(required = false) RequestDTO<NoticiaDTO> request);
}
