package susturismo.susturismo.web.api;


import susturismo.susturismo.domain.Formation;
import susturismo.susturismo.exeption.exeptions.HttpElementNotFoundExeption;
import susturismo.susturismo.exeption.exeptions.HttpInsertFailedException;
import susturismo.susturismo.exeption.exeptions.HttpUpdateFailedException;
import susturismo.susturismo.service.FormationService;
import susturismo.susturismo.web.dto.FormationDTO;
import susturismo.susturismo.web.dto.converter.FormationDTOConverter;
import susturismo.susturismo.web.dto.converter.responsesConverters.ResponseDTOConverter;
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
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class FormationController implements FormationApi{
    @Autowired
    FormationService formationService;
    @Autowired
    FormationDTOConverter formationDTOConverter;
    @Autowired
    ResponseDTOConverter responseDTOConverter;


    @Override
    public ResponseEntity<ResponseDTOList<FormationDTO>> findAll(RequestDTOList<FormationDTO> request) {
        List<FormationDTO> dtoList;
        HttpHeaders headers = new HttpHeaders();
        ResponseDTOList<FormationDTO> response;
        List<Formation> list = formationService.findAll();

        dtoList = list.stream().map(formationDTOConverter::convertToDTO).collect(Collectors.toList());

        headers.add("TotalElementCount", String.valueOf(list.size()));

        if (dtoList.isEmpty()) {

            throw new HttpElementNotFoundExeption("No Records of Formations Where Found");
        }
        response = responseDTOConverter.createResponseWithList(request, dtoList, "All Records Of Formations", true);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTOList<FormationDTO>> findAllActive(RequestDTOList<FormationDTO> request) {
        List<FormationDTO> dtoList;
        HttpHeaders headers = new HttpHeaders();
        ResponseDTOList<FormationDTO> response;
        List<Formation> list = formationService.findAllStatus("Active");

        dtoList = list.stream().map(formationDTOConverter::convertToDTO).collect(Collectors.toList());

        headers.add("TotalElementCount", String.valueOf(list.size()));

        if (dtoList.isEmpty()) {
            throw new HttpElementNotFoundExeption("No Records of Formations Active Where Found");
        }
        response = responseDTOConverter.createResponseWithList(request, dtoList, "All Records Of Formations", true);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTO<FormationDTO>> insert(RequestDTO<FormationDTO> request) {
        HttpHeaders headers = new HttpHeaders();
        ResponseDTO<FormationDTO> response;

        Formation value=formationService.insert(formationDTOConverter.convertToEntity(request.getRequest()));

        if(value==null) {
            throw new HttpInsertFailedException("Error to save");
        }

        FormationDTO dto= formationDTOConverter.convertToDTO(formationService.findById(value.getId()).get());
        response = responseDTOConverter.createResponse(request, dto, "Sucess", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTO<FormationDTO>> update(RequestDTO<FormationDTO> request) {
        HttpHeaders headers = new HttpHeaders();
        ResponseDTO<FormationDTO> response;
        Formation value=formationService.update(formationDTOConverter.convertToEntity(request.getRequest()));
        if(value==null) {
            throw new HttpUpdateFailedException("Error to update");
        }
        FormationDTO dto= formationDTOConverter.convertToDTO(formationService.findById(value.getId()).get());
        response = responseDTOConverter.createResponse(request, dto, "Sucess", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> active(RequestDTOList<UUID> request) {
        ResponseDTOList<FormationDTO> response;
        HttpHeaders headers = new HttpHeaders();
        request.getRequest().forEach(value->{
            if(formationService.updateStatus(value,"Active")){
                throw new HttpUpdateFailedException("Error to active Formation");
            }
        });
        response = responseDTOConverter.createResponseWithList(request, null, "Active", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
    @Override
    public ResponseEntity<Object> approve(RequestDTOList<UUID> request) {
        ResponseDTOList<FormationDTO> response;
        HttpHeaders headers = new HttpHeaders();
        request.getRequest().forEach(value->{
            if(formationService.updateStatus(value,"Active")){
                throw new HttpUpdateFailedException("Error to approve Formation");
            }
        });
        response = responseDTOConverter.createResponseWithList(request, null, "Approve", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> disable(RequestDTOList<UUID> request) {
        ResponseDTOList<FormationDTO> response;
        HttpHeaders headers = new HttpHeaders();
        request.getRequest().forEach(value->{
            if(formationService.updateStatus(value,"Disable")){
                throw new HttpUpdateFailedException("Error to disable Formation");
            }
        });

        response = responseDTOConverter.createResponseWithList(request, null, "Disable", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTO<FormationDTO>> findByID(UUID id, RequestDTO<FormationDTO> request) {
        FormationDTO dto = null;
        HttpHeaders headers = new HttpHeaders();
        ResponseDTO<FormationDTO> response;

        Optional<Formation> value = formationService.findById(id);

        if (value.isPresent()) {
            dto = formationDTOConverter.convertToDTO(value.get());

        }else{
            throw new HttpElementNotFoundExeption("Formation not exist by ID: "+id);
        }

        response = responseDTOConverter.createResponse(request, dto, "Search By Id", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
