package susturismo.susturismo.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import susturismo.susturismo.domain.Noticia;
import susturismo.susturismo.exeption.exeptions.HttpElementNotFoundExeption;
import susturismo.susturismo.exeption.exeptions.HttpInsertFailedException;
import susturismo.susturismo.exeption.exeptions.HttpUpdateFailedException;
import susturismo.susturismo.service.NoticiaService;
import susturismo.susturismo.web.dto.NoticiaDTO;
import susturismo.susturismo.web.dto.converter.NoticiaDTOConverter;
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
public class NoticiaController implements NoticiaApi{
    @Autowired
    NoticiaService noticiaService;
    @Autowired
    NoticiaDTOConverter noticiaDTOConverter;
    @Autowired
    ResponseDTOConverter responseDTOConverter;
    @Override
    public ResponseEntity<ResponseDTOList<NoticiaDTO>> findAll(RequestDTOList<NoticiaDTO> request) {
        List<NoticiaDTO> dtoList;
        HttpHeaders headers = new HttpHeaders();
        ResponseDTOList<NoticiaDTO> response;
        List<Noticia> list = noticiaService.findAll();

        dtoList = list.stream().map(noticiaDTOConverter::convertToDTO).collect(Collectors.toList());

        headers.add("TotalElementCount", String.valueOf(list.size()));

        if (dtoList.isEmpty()) {

            throw new HttpElementNotFoundExeption("No Records of Noticias Where Found");
        }
        response = responseDTOConverter.createResponseWithList(request, dtoList, "All Records Of Noticias", true);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTOList<NoticiaDTO>> findAllLimit(RequestDTOList<NoticiaDTO> request) {
        List<NoticiaDTO> dtoList;
        HttpHeaders headers = new HttpHeaders();
        ResponseDTOList<NoticiaDTO> response;
        List<Noticia> list = noticiaService.findAllLimit();

        dtoList = list.stream().map(noticiaDTOConverter::convertToDTO).collect(Collectors.toList());

        headers.add("TotalElementCount", String.valueOf(list.size()));

        if (dtoList.isEmpty()) {

            throw new HttpElementNotFoundExeption("No Records of Noticias Where Found");
        }
        response = responseDTOConverter.createResponseWithList(request, dtoList, "All Records Of Noticias", true);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
    @Override
    public ResponseEntity<ResponseDTOList<NoticiaDTO>> findAllByUser(RequestDTOList<NoticiaDTO> request) {
        List<NoticiaDTO> dtoList;
        HttpHeaders headers = new HttpHeaders();
        ResponseDTOList<NoticiaDTO> response;
        List<Noticia> list = noticiaService.findAllByUser();

        dtoList = list.stream().map(noticiaDTOConverter::convertToDTO).collect(Collectors.toList());

        headers.add("TotalElementCount", String.valueOf(list.size()));

        if (dtoList.isEmpty()) {

            throw new HttpElementNotFoundExeption("No Records of Noticias Where Found");
        }
        response = responseDTOConverter.createResponseWithList(request, dtoList, "All Records Of Noticias", true);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTOList<NoticiaDTO>> findAllActive(RequestDTOList<NoticiaDTO> request) {
        List<NoticiaDTO> dtoList;
        HttpHeaders headers = new HttpHeaders();
        ResponseDTOList<NoticiaDTO> response;
        List<Noticia> list = noticiaService.findAllStatus("Active");
        /*
        resolver problema de account em noticia que estava null
      list.forEach(v->{
           noticiaService.updateExtra(v);
       });
      list = noticiaService.findAllStatus("Active");*/
        dtoList = list.stream().map(noticiaDTOConverter::convertToDTO).collect(Collectors.toList());

        headers.add("TotalElementCount", String.valueOf(list.size()));

        if (dtoList.isEmpty()) {
            throw new HttpElementNotFoundExeption("No Records of Noticias Active Where Found");
        }
        response = responseDTOConverter.createResponseWithList(request, dtoList, "All Records Of Noticias", true);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTOList<NoticiaDTO>> findAllApprove(RequestDTOList<NoticiaDTO> request) {
        List<NoticiaDTO> dtoList;
        HttpHeaders headers = new HttpHeaders();
        ResponseDTOList<NoticiaDTO> response;
        List<Noticia> list = noticiaService.findAllStatus("Pendente");

        dtoList = list.stream().map(noticiaDTOConverter::convertToDTO).collect(Collectors.toList());

        headers.add("TotalElementCount", String.valueOf(list.size()));

        if (dtoList.isEmpty()) {
            throw new HttpElementNotFoundExeption("No Records of Noticias Active Where Found");
        }
        response = responseDTOConverter.createResponseWithList(request, dtoList, "All Records Of Noticias", true);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTO<NoticiaDTO>> insert(RequestDTO<NoticiaDTO> request) {
        HttpHeaders headers = new HttpHeaders();
        ResponseDTO<NoticiaDTO> response;

        Noticia noticia=noticiaService.insert(noticiaDTOConverter.convertToEntity(request.getRequest()));

        if(noticia==null) {
            throw new HttpInsertFailedException("Error to save");
        }

        NoticiaDTO dto= noticiaDTOConverter.convertToDTO(noticiaService.findById(noticia.getId()).get());
        response = responseDTOConverter.createResponse(request, dto, "Search By Id", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ResponseDTO<NoticiaDTO>> update(RequestDTO<NoticiaDTO> request) {
        HttpHeaders headers = new HttpHeaders();
        ResponseDTO<NoticiaDTO> response;

        Noticia noticia=noticiaService.update(noticiaDTOConverter.convertToEntity(request.getRequest()));

        if(noticia==null) {
            throw new HttpUpdateFailedException("Error to update");
        }

        NoticiaDTO dto= noticiaDTOConverter.convertToDTO(noticiaService.findById(noticia.getId()).get());
        response = responseDTOConverter.createResponse(request, dto, "Search By Id", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> active(RequestDTO<NoticiaDTO> request) {
        ResponseDTO<NoticiaDTO> response;
        HttpHeaders headers = new HttpHeaders();
        UUID value=request.getRequest().getId();
        if(noticiaService.updateStatus(value,"Active")){
            throw new HttpUpdateFailedException("Error to active Noticia");
        }
        response = responseDTOConverter.createResponse(request, null, "Active", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
    @Override
    public ResponseEntity<Object> approve(RequestDTO<NoticiaDTO> request) {

        ResponseDTO<NoticiaDTO> response;
        HttpHeaders headers = new HttpHeaders();
        UUID value=request.getRequest().getId();
        if(noticiaService.updateStatus(value,"Active")){
            throw new HttpUpdateFailedException("Error to approve Noticia");
        }
        response = responseDTOConverter.createResponse(request, null, "Approve", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> disable(RequestDTO<NoticiaDTO> request) {
        ResponseDTO<NoticiaDTO> response;
        HttpHeaders headers = new HttpHeaders();
        UUID value=request.getRequest().getId();
        if(noticiaService.updateStatus(value,"Disable")){
            throw new HttpUpdateFailedException("Error to disable noticia");
        }
        response = responseDTOConverter.createResponse(request, null, "Disables", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTO<NoticiaDTO>> findByID(UUID id, RequestDTO<NoticiaDTO> request) {
        NoticiaDTO dto = null;
        HttpHeaders headers = new HttpHeaders();
        ResponseDTO<NoticiaDTO> response;

        Optional<Noticia> noticia = noticiaService.findById(id);

        if (noticia.isPresent()) {
            dto = noticiaDTOConverter.convertToDTO(noticia.get());

        }else{
            throw new HttpElementNotFoundExeption("Noticia not exist by ID: "+id);
        }

        response = responseDTOConverter.createResponse(request, dto, "Search By Id", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTO<NoticiaDTO>> delete(UUID id, RequestDTO<NoticiaDTO> request) {
        NoticiaDTO dto = null;
        HttpHeaders headers = new HttpHeaders();
        ResponseDTO<NoticiaDTO> response;
        Optional<Noticia> noticia = noticiaService.findById(id);
        if (noticia.isPresent()) {
          noticiaService.delete(id);

        }else{
            throw new HttpElementNotFoundExeption("Noticia not exist by ID: "+id);
        }
        response = responseDTOConverter.createResponse(request, dto, "Search By Id", true);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }


}
