package susturismo.susturismo.web.api;

import susturismo.susturismo.web.dto.AccountDTO;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTO;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTOList;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTO;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTOList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("api/v1/account")
public interface AccountApi {

    @GetMapping(path = "/all")
    ResponseEntity<ResponseDTOList<AccountDTO>> findAll(@RequestBody(required = false) RequestDTOList<AccountDTO> request);
    @PutMapping(path = "")
    ResponseEntity<ResponseDTO<AccountDTO>> update(@RequestBody(required = false) RequestDTO<AccountDTO> request);
    @PostMapping(path = "/active")
    ResponseEntity<Object> active(@RequestBody(required = false) RequestDTO<AccountDTO> request);
    @PostMapping(path = "/disable")
    ResponseEntity<Object> disable(@RequestBody(required = false) RequestDTO<AccountDTO> request);
    @PostMapping(path = "/remove")
    ResponseEntity<Object> remove(@RequestBody(required = false) RequestDTO<AccountDTO> request);
    @GetMapping(path = "/{id}")
    ResponseEntity<ResponseDTO<AccountDTO>> findByID(
            @PathVariable(value = "id") UUID eventId,
            @RequestBody(required = false) RequestDTO<AccountDTO> request);
}
