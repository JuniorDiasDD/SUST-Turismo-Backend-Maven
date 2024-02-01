package susturismo.susturismo.web.api;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import susturismo.susturismo.web.dto.PastaDTO;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTO;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTOList;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTO;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTOList;

import java.util.UUID;

@RequestMapping("api/v1/pasta")
public interface PastaApi {

    @GetMapping(path = "")
    ResponseEntity<ResponseDTOList<PastaDTO>> findAll(@RequestBody(required = false) RequestDTOList<PastaDTO> request);

    @PostMapping(path = "")
    ResponseEntity<ResponseDTO<PastaDTO>> insert(@RequestBody(required = false) @Valid RequestDTO<PastaDTO> request);
    @PutMapping(path = "")
    ResponseEntity<ResponseDTO<PastaDTO>> edit(@RequestBody(required = false) @Valid RequestDTO<PastaDTO> request);
    @DeleteMapping(path = "/{id}")
    ResponseEntity<ResponseDTO<PastaDTO>> delete(
            @PathVariable(value = "id") UUID id,
            @RequestBody(required = false) RequestDTO<PastaDTO> request);


}
