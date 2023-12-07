package susturismo.susturismo.web.api;

import susturismo.susturismo.web.dto.RoleDTO;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTO;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTOList;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTO;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTOList;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("api/v1/role")
public interface RoleApi {

    @GetMapping(path = "/all")
    ResponseEntity<ResponseDTOList<RoleDTO>> findAll(@RequestBody(required = false) RequestDTOList<RoleDTO> request);

    @PostMapping(path = "")
    ResponseEntity<ResponseDTO<RoleDTO>> insert(@RequestBody(required = false) @Valid RequestDTO<RoleDTO> request);

    @PutMapping(path = "")
    ResponseEntity<ResponseDTO<RoleDTO>> update(@RequestBody(required = false) RequestDTO<RoleDTO> request);

    @PostMapping(path = "/active")
    ResponseEntity<Object> active(@RequestBody(required = false) RequestDTOList<UUID> request);
    @PostMapping(path = "/disable")
    ResponseEntity<Object> disable(@RequestBody(required = false) RequestDTOList<UUID> request);

    @GetMapping(path = "/{id}")
    ResponseEntity<ResponseDTO<RoleDTO>> findByID(
            @PathVariable(value = "id") UUID id,
            @RequestBody(required = false) RequestDTO<RoleDTO> request);
}
