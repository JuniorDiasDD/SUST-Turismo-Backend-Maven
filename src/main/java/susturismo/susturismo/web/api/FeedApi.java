package susturismo.susturismo.web.api;


import susturismo.susturismo.web.dto.FeedDTO;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTO;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTOList;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTO;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTOList;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("api/v1/feed")
public interface FeedApi {

    @GetMapping(path = "/all")
    public ResponseEntity<ResponseDTOList<FeedDTO>> findAll(@RequestBody(required = false) RequestDTOList<FeedDTO> request);

    @GetMapping(path = "")
    public ResponseEntity<ResponseDTOList<FeedDTO>> findAllActive(@RequestBody(required = false) RequestDTOList<FeedDTO> request);

    @PostMapping(path = "")
    ResponseEntity<ResponseDTO<FeedDTO>> insert(@RequestBody(required = false) @Valid RequestDTO<FeedDTO> request);

    @PutMapping(path = "")
    ResponseEntity<ResponseDTO<FeedDTO>> update(@RequestBody(required = false) RequestDTO<FeedDTO> request);

    @PostMapping(path = "/active")
    ResponseEntity<Object> active(@RequestBody(required = false) RequestDTOList<UUID> request);
    @PostMapping(path = "/disable")
    ResponseEntity<Object> disable(@RequestBody(required = false) RequestDTOList<UUID> request);


    @GetMapping(path = "/{id}")
    ResponseEntity<ResponseDTO<FeedDTO>> findByID(
            @PathVariable(value = "id") UUID id,
            @RequestBody(required = false) RequestDTO<FeedDTO> request);
}
