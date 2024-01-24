package susturismo.susturismo.web.api;

import susturismo.susturismo.domain.Formation;
import susturismo.susturismo.domain.Role;
import susturismo.susturismo.exeption.exeptions.HttpElementNotFoundExeption;
import susturismo.susturismo.exeption.exeptions.HttpInsertFailedException;
import susturismo.susturismo.exeption.exeptions.HttpUpdateFailedException;
import susturismo.susturismo.service.FormationService;
import susturismo.susturismo.service.RoleService;
import susturismo.susturismo.web.dto.FormationDTO;
import susturismo.susturismo.web.dto.RoleDTO;
import susturismo.susturismo.web.dto.converter.FormationDTOConverter;
import susturismo.susturismo.web.dto.converter.RoleDTOConverter;
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
public class RoleController implements RoleApi{
    @Autowired
    RoleService roleService;
    @Autowired
    RoleDTOConverter roleDTOConverter;
    @Autowired
    ResponseDTOConverter responseDTOConverter;


    @Override
    public ResponseEntity<ResponseDTOList<RoleDTO>> findAll(RequestDTOList<RoleDTO> request) {
        List<RoleDTO> dtoList;
        HttpHeaders headers = new HttpHeaders();
        ResponseDTOList<RoleDTO> response;
        List<Role> list = roleService.findAll();

        dtoList = list.stream().map(roleDTOConverter::convertToDTO).collect(Collectors.toList());

        headers.add("TotalElementCount", String.valueOf(list.size()));

        if (dtoList.isEmpty()) {

            throw new HttpElementNotFoundExeption("No Records of Roles Where Found");
        }
        response = responseDTOConverter.createResponseWithList(request, dtoList, "All Records Of Roles", true);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTO<RoleDTO>> insert(RequestDTO<RoleDTO> request) {
        HttpHeaders headers = new HttpHeaders();
        ResponseDTO<RoleDTO> response;

        Role value=roleService.insert(roleDTOConverter.convertToEntity(request.getRequest()));

        if(value==null) {
            throw new HttpInsertFailedException("Error to save");
        }

        response = responseDTOConverter.createResponse(request, null, "Success", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTO<RoleDTO>> update(RequestDTO<RoleDTO> request) {
        HttpHeaders headers = new HttpHeaders();
        ResponseDTO<RoleDTO> response;
        Role value=roleService.update(roleDTOConverter.convertToEntity(request.getRequest()));
        if(value==null) {
            throw new HttpUpdateFailedException("Error to update");
        }

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> active(RequestDTOList<UUID> request) {
        HttpHeaders headers = new HttpHeaders();
        ResponseDTOList<RoleDTO> response;
        request.getRequest().forEach(value->{
            if(roleService.updateStatus(value,"Active")){
                throw new HttpUpdateFailedException("Error to active Role");
            }
        });
        response = responseDTOConverter.createResponseWithList(request, null, "Ative", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> disable(RequestDTOList<UUID> request) {
        HttpHeaders headers = new HttpHeaders();
        ResponseDTOList<RoleDTO> response;
        request.getRequest().forEach(value->{
            if(roleService.updateStatus(value,"Disable")){
                throw new HttpUpdateFailedException("Error to disable Role");
            }
        });

        response = responseDTOConverter.createResponseWithList(request, null, "Disable", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTO<RoleDTO>> findByID(UUID id, RequestDTO<RoleDTO> request) {
        RoleDTO dto = null;
        HttpHeaders headers = new HttpHeaders();
        ResponseDTO<RoleDTO> response;

        Optional<Role> value = roleService.findById(id);

        if (value.isPresent()) {
            dto = roleDTOConverter.convertToDTO(value.get());

        }else{
            throw new HttpElementNotFoundExeption("Role not exist by ID: "+id);
        }

        response = responseDTOConverter.createResponse(request, dto, "Search By Id", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
