package susturismo.susturismo.web.api;

import susturismo.susturismo.web.dto.PortfolioDTO;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTO;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTOList;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTO;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTOList;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("api/v1/portfolio")
public interface PortfolioApi {

    @GetMapping(path = "/all")
    ResponseEntity<ResponseDTOList<PortfolioDTO>> findAll(@RequestBody(required = false) RequestDTOList<PortfolioDTO> request);

    @PostMapping(path = "")
    ResponseEntity<ResponseDTO<PortfolioDTO>> insert(@RequestBody(required = false) @Valid RequestDTO<PortfolioDTO> request);

    @PutMapping(path = "")
    ResponseEntity<ResponseDTO<PortfolioDTO>> update(@RequestBody(required = false) RequestDTO<PortfolioDTO> request);

    @PostMapping(path = "/active")
    ResponseEntity<Object> active(@RequestBody(required = false) RequestDTOList<UUID> request);
    @PostMapping(path = "/disable")
    ResponseEntity<Object> disable(@RequestBody(required = false) RequestDTOList<UUID> request);
    @PostMapping(path = "/remove")
    ResponseEntity<Object> remove(@RequestBody(required = false) RequestDTO<PortfolioDTO> request);

    @GetMapping(path = "/{id}")
    ResponseEntity<ResponseDTO<PortfolioDTO>> findByID(
            @PathVariable(value = "id") UUID id,
            @RequestBody(required = false) RequestDTO<PortfolioDTO> request);
}
