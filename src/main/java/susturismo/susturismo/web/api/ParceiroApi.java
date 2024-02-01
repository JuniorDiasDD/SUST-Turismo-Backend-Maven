package susturismo.susturismo.web.api;


import susturismo.susturismo.web.dto.ParceiroDTO;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTO;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTOList;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTO;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTOList;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("api/v1/parceiro")
public interface ParceiroApi {

    @GetMapping(path = "/all")
    ResponseEntity<ResponseDTOList<ParceiroDTO>> findAllParceiro(@RequestBody(required = false) RequestDTOList<ParceiroDTO> request);

    @GetMapping(path = "")
    ResponseEntity<ResponseDTOList<ParceiroDTO>> findAllParceiroActive(@RequestBody(required = false) RequestDTOList<ParceiroDTO> request);

    @PostMapping(path = "")
    ResponseEntity<ResponseDTO<ParceiroDTO>> insert(@RequestBody(required = false) @Valid RequestDTO<ParceiroDTO> request);

    @PutMapping(path = "")
    ResponseEntity<ResponseDTO<ParceiroDTO>> update(@RequestBody(required = false) RequestDTO<ParceiroDTO> request);

    @PostMapping(path = "/active")
    ResponseEntity<Object> active(@RequestBody(required = false) RequestDTOList<UUID> request);
    @PostMapping(path = "/disable")
    ResponseEntity<Object> disable(@RequestBody(required = false) RequestDTOList<UUID> request);
}
