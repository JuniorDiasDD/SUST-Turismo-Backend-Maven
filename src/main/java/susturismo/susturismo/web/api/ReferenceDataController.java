package susturismo.susturismo.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import susturismo.susturismo.domain.Pais;
import susturismo.susturismo.domain.Universidade;
import susturismo.susturismo.exeption.exeptions.HttpElementNotFoundExeption;
import susturismo.susturismo.service.ReferenceDataService;
import susturismo.susturismo.web.dto.PaisDTO;
import susturismo.susturismo.web.dto.UniversidadeDTO;
import susturismo.susturismo.web.dto.converter.PaisDTOConverter;
import susturismo.susturismo.web.dto.converter.UniversidadeDTOConverter;
import susturismo.susturismo.web.dto.converter.responsesConverters.ResponseDTOConverter;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTOList;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTOList;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class ReferenceDataController implements ReferenceDataApi{

    @Autowired
    ReferenceDataService referenceDataService;
@Autowired
    UniversidadeDTOConverter universidadeDTOConverter;
@Autowired
    PaisDTOConverter paisDTOConverter;
    @Autowired
    ResponseDTOConverter responseDTOConverter;

    @Override
    public ResponseEntity<ResponseDTOList<UniversidadeDTO>> universidade(RequestDTOList<UniversidadeDTO> request) {
        List<UniversidadeDTO> dtoList;
        HttpHeaders headers = new HttpHeaders();
        ResponseDTOList<UniversidadeDTO> response;
        List<Universidade> list = referenceDataService.findAllUniversidade();

        dtoList = list.stream().map(universidadeDTOConverter::convertToDTO).collect(Collectors.toList());

        headers.add("TotalElementCount", String.valueOf(list.size()));

        if (dtoList.isEmpty()) {

            throw new HttpElementNotFoundExeption("No Records of Universidades Where Found");
        }
        response = responseDTOConverter.createResponseWithList(request, dtoList, "All Records Of Universidades", true);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTOList<PaisDTO>> pais(RequestDTOList<PaisDTO> request) {
        List<PaisDTO> dtoList;
        HttpHeaders headers = new HttpHeaders();
        ResponseDTOList<PaisDTO> response;
        List<Pais> list = referenceDataService.findAllPais();

        dtoList = list.stream().map(paisDTOConverter::convertToDTO).collect(Collectors.toList());

        headers.add("TotalElementCount", String.valueOf(list.size()));

        if (dtoList.isEmpty()) {

            throw new HttpElementNotFoundExeption("No Records of Pais Where Found");
        }
        response = responseDTOConverter.createResponseWithList(request, dtoList, "All Records Of Paises", true);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
