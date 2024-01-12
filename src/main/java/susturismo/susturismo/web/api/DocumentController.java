package susturismo.susturismo.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import susturismo.susturismo.domain.Document;
import susturismo.susturismo.domain.Equipa;
import susturismo.susturismo.domain.Event;
import susturismo.susturismo.domain.Feed;
import susturismo.susturismo.exeption.exeptions.HttpElementNotFoundExeption;
import susturismo.susturismo.exeption.exeptions.HttpInsertFailedException;
import susturismo.susturismo.service.DocumentService;
import susturismo.susturismo.service.EquipaService;
import susturismo.susturismo.web.dto.DocumentDTO;
import susturismo.susturismo.web.dto.EquipaDTO;
import susturismo.susturismo.web.dto.EventDTO;
import susturismo.susturismo.web.dto.FeedDTO;
import susturismo.susturismo.web.dto.converter.DocumentDTOConverter;
import susturismo.susturismo.web.dto.converter.EquipaDTOConverter;
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
public class DocumentController implements DocumentApi{
    @Autowired
    DocumentService documentService;
    @Autowired
    DocumentDTOConverter documentDTOConverter;
    @Autowired
    ResponseDTOConverter responseDTOConverter;


    @Override
    public ResponseEntity<ResponseDTOList<DocumentDTO>> findAll(RequestDTOList<DocumentDTO> request) {
        List<DocumentDTO> dtoList;
        HttpHeaders headers = new HttpHeaders();
        ResponseDTOList<DocumentDTO> response;
        List<Document> list = documentService.findAll();

        dtoList = list.stream().map(documentDTOConverter::convertToDTO).collect(Collectors.toList());

        headers.add("TotalElementCount", String.valueOf(list.size()));

        if (dtoList.isEmpty()) {

            throw new HttpElementNotFoundExeption("No Records of Documents Where Found");
        }
        response = responseDTOConverter.createResponseWithList(request, dtoList, "All Records Of Documents", true);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTOList<DocumentDTO>> findAllByUser(RequestDTOList<DocumentDTO> request) {
        List<DocumentDTO> dtoList;
        HttpHeaders headers = new HttpHeaders();
        ResponseDTOList<DocumentDTO> response;
        List<Document> list = documentService.findAll();

        dtoList = list.stream().map(documentDTOConverter::convertToDTO).collect(Collectors.toList());

        headers.add("TotalElementCount", String.valueOf(list.size()));

        if (dtoList.isEmpty()) {

            throw new HttpElementNotFoundExeption("No Records of Documents Where Found");
        }
        response = responseDTOConverter.createResponseWithList(request, dtoList, "All Records Of Documents", true);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseDTO<DocumentDTO>> insert(RequestDTO<DocumentDTO> request) {
        HttpHeaders headers = new HttpHeaders();
        ResponseDTO<DocumentDTO> response;

        Document document=documentService.insert(documentDTOConverter.convertToEntity(request.getRequest()));

        if(document==null) {
            throw new HttpInsertFailedException("Error to save");
        }

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<ResponseDTO<DocumentDTO>> delete(UUID id, RequestDTO<DocumentDTO> request) {
        DocumentDTO dto = null;
        HttpHeaders headers = new HttpHeaders();
        ResponseDTO<DocumentDTO> response;
        Optional<Document> document = documentService.findById(id);
        if (document.isPresent()) {
            documentService.delete(id);

        }else{
            throw new HttpElementNotFoundExeption("Document not exist by ID: "+id);
        }
        response = responseDTOConverter.createResponse(request, dto, "Search By Id", true);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
