package susturismo.susturismo.web.api;


import susturismo.susturismo.web.dto.FormationDTO;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTO;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTOList;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTO;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTOList;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("api/v1/formation")
public interface FormationApi {

    @GetMapping(path = "/all")
    public ResponseEntity<ResponseDTOList<FormationDTO>> findAll(@RequestBody(required = false) RequestDTOList<FormationDTO> request);

    @GetMapping(path = "")
    public ResponseEntity<ResponseDTOList<FormationDTO>> findAllActive(@RequestBody(required = false) RequestDTOList<FormationDTO> request);

    @PostMapping(path = "")
    ResponseEntity<ResponseDTO<FormationDTO>> insert(@RequestBody(required = false) @Valid RequestDTO<FormationDTO> request);

    @PutMapping(path = "")
    ResponseEntity<ResponseDTO<FormationDTO>> update(@RequestBody(required = false) RequestDTO<FormationDTO> request);

    @PostMapping(path = "/active")
    ResponseEntity<Object> active(@RequestBody(required = false) RequestDTO<FormationDTO> request);
    @PostMapping(path = "/approve")
    ResponseEntity<Object> approve(@RequestBody(required = false) RequestDTO<FormationDTO> request);
    @PostMapping(path = "/disable")
    ResponseEntity<Object> disable(@RequestBody(required = false) RequestDTO<FormationDTO> request);

    @GetMapping(path = "/{id}")
    ResponseEntity<ResponseDTO<FormationDTO>> findByID(
            @PathVariable(value = "id") UUID id,
            @RequestBody(required = false) RequestDTO<FormationDTO> request);
}
