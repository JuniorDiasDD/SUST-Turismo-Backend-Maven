package susturismo.susturismo.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import susturismo.susturismo.domain.Equipa;
import susturismo.susturismo.domain.Event;
import susturismo.susturismo.exeption.exeptions.HttpElementNotFoundExeption;
import susturismo.susturismo.exeption.exeptions.HttpInsertFailedException;
import susturismo.susturismo.exeption.exeptions.HttpUpdateFailedException;
import susturismo.susturismo.service.EquipaService;
import susturismo.susturismo.service.EventService;
import susturismo.susturismo.web.dto.EquipaDTO;
import susturismo.susturismo.web.dto.EventDTO;
import susturismo.susturismo.web.dto.converter.EquipaDTOConverter;
import susturismo.susturismo.web.dto.converter.EventDTOConverter;
import susturismo.susturismo.web.dto.converter.responsesConverters.ResponseDTOConverter;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTO;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTOList;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTO;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTOList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class EquipaController implements EquipaApi{
    @Autowired
    EquipaService equipaService;
    @Autowired
    EquipaDTOConverter equipaDTOConverter;
    @Autowired
    ResponseDTOConverter responseDTOConverter;
    @Override
    public ResponseEntity<ResponseDTOList<EquipaDTO>> findAll(RequestDTOList<EquipaDTO> request) {
        List<EquipaDTO> dtoList;
        HttpHeaders headers = new HttpHeaders();
        ResponseDTOList<EquipaDTO> response;
        List<Equipa> list = equipaService.findAll();

        dtoList = list.stream().map(equipaDTOConverter::convertToDTO).collect(Collectors.toList());

        headers.add("TotalElementCount", String.valueOf(list.size()));

        if (dtoList.isEmpty()) {

            throw new HttpElementNotFoundExeption("No Records of Equipa Where Found");
        }
        response = responseDTOConverter.createResponseWithList(request, dtoList, "All Records Of Equipa", true);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }


}
