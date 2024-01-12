package susturismo.susturismo.web.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import susturismo.susturismo.web.dto.EquipaDTO;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTOList;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTOList;

@RequestMapping("api/v1/equipa")
public interface EquipaApi {

    @GetMapping(path = "")
    public ResponseEntity<ResponseDTOList<EquipaDTO>> findAll(@RequestBody(required = false) RequestDTOList<EquipaDTO> request);

}
