package susturismo.susturismo.web.api;

import susturismo.susturismo.domain.Parceiro;
import susturismo.susturismo.exeption.exeptions.HttpElementNotFoundExeption;
import susturismo.susturismo.exeption.exeptions.HttpInsertFailedException;
import susturismo.susturismo.exeption.exeptions.HttpUpdateFailedException;
import susturismo.susturismo.service.ParceiroService;
import susturismo.susturismo.web.dto.converter.ParceiroDTOConverter;
import susturismo.susturismo.web.dto.converter.responsesConverters.ResponseDTOConverter;
import susturismo.susturismo.web.dto.ParceiroDTO;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTO;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTOList;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTO;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTOList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class ParceiroController implements ParceiroApi{
    @Autowired
    ParceiroService parceiroService;
    @Autowired
    ParceiroDTOConverter parceiroDTOConverter;
    @Autowired
    ResponseDTOConverter responseDTOConverter;


    @Override
    public ResponseEntity<ResponseDTOList<ParceiroDTO>> findAllParceiro(RequestDTOList<ParceiroDTO> request) {
        List<ParceiroDTO> dtoList;
        HttpHeaders headers = new HttpHeaders();
        ResponseDTOList<ParceiroDTO> response;
        List<Parceiro> list = parceiroService.findAll();

        dtoList = list.stream().map(parceiroDTOConverter::convertToDTO).collect(Collectors.toList());

        headers.add("TotalElementCount", String.valueOf(list.size()));

        if (dtoList.isEmpty()) {

            throw new HttpElementNotFoundExeption("No Records of Parceiros Where Found");
        }
        response = responseDTOConverter.createResponseWithList(request, dtoList, "All Records Of Parceiros", true);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTOList<ParceiroDTO>> findAllParceiroActive(RequestDTOList<ParceiroDTO> request) {
        List<ParceiroDTO> dtoList;
        HttpHeaders headers = new HttpHeaders();
        ResponseDTOList<ParceiroDTO> response;
        List<Parceiro> list = parceiroService.findAllStatus("Active");

        dtoList = list.stream().map(parceiroDTOConverter::convertToDTO).collect(Collectors.toList());

        headers.add("TotalElementCount", String.valueOf(list.size()));

        if (dtoList.isEmpty()) {
            throw new HttpElementNotFoundExeption("No Records of Parceiros Active Where Found");
        }
        response = responseDTOConverter.createResponseWithList(request, dtoList, "All Records Of Parceiros", true);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTO<ParceiroDTO>> insert(RequestDTO<ParceiroDTO> request) {
        HttpHeaders headers = new HttpHeaders();
        ResponseDTO<ParceiroDTO> response;

        Parceiro parceiro=parceiroService.insert(parceiroDTOConverter.convertToEntity(request.getRequest()));

        if(parceiro==null) {
            throw new HttpInsertFailedException("Error to save");
        }

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<ResponseDTO<ParceiroDTO>> update(RequestDTO<ParceiroDTO> request) {
        HttpHeaders headers = new HttpHeaders();
        ResponseDTO<ParceiroDTO> response;

        Parceiro value=parceiroService.update(parceiroDTOConverter.convertToEntity(request.getRequest()));

        if(value==null) {
            throw new HttpUpdateFailedException("Error to update");
        }

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> active(RequestDTOList<UUID> request) {
        request.getRequest().forEach(value->{
            if(parceiroService.updateStatus(value,"Active")){
                throw new HttpUpdateFailedException("Error to active parceiro");
            }
        });

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> disable(RequestDTOList<UUID> request) {
        request.getRequest().forEach(value->{
            if(parceiroService.updateStatus(value,"Disable")){
                throw new HttpUpdateFailedException("Error to disable parceiro");
            }
        });

        return ResponseEntity.ok().build();
    }
}
