package susturismo.susturismo.web.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import susturismo.susturismo.web.dto.PaisDTO;
import susturismo.susturismo.web.dto.UniversidadeDTO;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTOList;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTOList;


@RequestMapping("api/v1/referenceData")
public interface ReferenceDataApi {

    @GetMapping(path = "/universidades")
    ResponseEntity<ResponseDTOList<UniversidadeDTO>> universidade(@RequestBody(required = false) RequestDTOList<UniversidadeDTO> request);
    @GetMapping(path = "/pais")
    ResponseEntity<ResponseDTOList<PaisDTO>> pais(@RequestBody(required = false) RequestDTOList<PaisDTO> request);


}
