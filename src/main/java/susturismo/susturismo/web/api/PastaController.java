package susturismo.susturismo.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import susturismo.susturismo.domain.Pasta;
import susturismo.susturismo.exeption.exeptions.HttpElementNotFoundExeption;
import susturismo.susturismo.exeption.exeptions.HttpInsertFailedException;
import susturismo.susturismo.service.PastaService;
import susturismo.susturismo.web.dto.PastaDTO;
import susturismo.susturismo.web.dto.converter.PastaDTOConverter;
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
public class PastaController implements PastaApi{
    @Autowired
    PastaService pastaService;
    @Autowired
    PastaDTOConverter pastaDTOConverter;
    @Autowired
    ResponseDTOConverter responseDTOConverter;





    @Override
    public ResponseEntity<ResponseDTO<PastaDTO>> insert(RequestDTO<PastaDTO> request) {
        HttpHeaders headers = new HttpHeaders();
        ResponseDTO<PastaDTO> response;

        Pasta pasta=pastaService.insert(pastaDTOConverter.convertToEntity(request.getRequest()));

        if(pasta==null) {
            throw new HttpInsertFailedException("Error to save");
        }

        PastaDTO dto= pastaDTOConverter.convertToDTO(pasta);
        response = responseDTOConverter.createResponse(request, dto, "Sucess", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTO<PastaDTO>> edit(RequestDTO<PastaDTO> request) {
        HttpHeaders headers = new HttpHeaders();
        ResponseDTO<PastaDTO> response;

        Pasta pasta=pastaService.update(pastaDTOConverter.convertToEntity(request.getRequest()));

        if(pasta==null) {
            throw new HttpInsertFailedException("Error to edit");
        }

        PastaDTO dto= pastaDTOConverter.convertToDTO(pasta);
        response = responseDTOConverter.createResponse(request, dto, "Edit Success", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTO<PastaDTO>> delete(UUID id, RequestDTO<PastaDTO> request) {
        PastaDTO dto = null;
        HttpHeaders headers = new HttpHeaders();
        ResponseDTO<PastaDTO> response;
        Optional<Pasta> pasta = pastaService.findById(id);
        if (pasta.isPresent()) {
            pastaService.delete(id);

        }else{
            throw new HttpElementNotFoundExeption("Pasta not exist by ID: "+id);
        }
        response = responseDTOConverter.createResponse(request, dto, "Search By Id", true);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTOList<PastaDTO>> findAll(RequestDTOList<PastaDTO> request) {
        List<PastaDTO> dtoList;
        HttpHeaders headers = new HttpHeaders();
        ResponseDTOList<PastaDTO> response;
        List<Pasta> list = pastaService.findAll();

        dtoList = list.stream().map(pastaDTOConverter::convertToDTOFull).collect(Collectors.toList());

        headers.add("TotalElementCount", String.valueOf(list.size()));

        if (dtoList.isEmpty()) {

            throw new HttpElementNotFoundExeption("No Records of Pastas Where Found");
        }
        response = responseDTOConverter.createResponseWithList(request, dtoList, "All Records Of Pasta", true);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }


}
