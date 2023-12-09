package susturismo.susturismo.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
    public ResponseEntity<ResponseDTOList<NoticiaDTO>> findAllActive(RequestDTOList<NoticiaDTO> request) {
        List<NoticiaDTO> dtoList;
        HttpHeaders headers = new HttpHeaders();
        ResponseDTOList<NoticiaDTO> response;
        List<Noticia> list = noticiaService.findAllStatus("Active");

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

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<ResponseDTO<NoticiaDTO>> update(RequestDTO<NoticiaDTO> request) {
        HttpHeaders headers = new HttpHeaders();
        ResponseDTO<NoticiaDTO> response;

        Noticia noticia=noticiaService.update(noticiaDTOConverter.convertToEntity(request.getRequest()));

        if(noticia==null) {
            throw new HttpUpdateFailedException("Error to update");
        }

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> active(RequestDTOList<UUID> request) {
        request.getRequest().forEach(value->{
            if(noticiaService.updateStatus(value,"Active")){
                throw new HttpUpdateFailedException("Error to active Noticia");
            }
        });

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> disable(RequestDTOList<UUID> request) {
        request.getRequest().forEach(value->{
            if(noticiaService.updateStatus(value,"Disable")){
                throw new HttpUpdateFailedException("Error to disable noticia");
            }
        });

        return ResponseEntity.ok().build();
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
