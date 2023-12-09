package susturismo.susturismo.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import susturismo.susturismo.domain.File;
import susturismo.susturismo.exeption.exeptions.HttpInsertFailedException;
import susturismo.susturismo.service.FileService;
import susturismo.susturismo.web.dto.FileDTO;
import susturismo.susturismo.web.dto.converter.FileDTOConverter;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTO;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTO;

@RestController
public class FileController implements FileApi{

@Autowired
    FileService fileService;
@Autowired
    FileDTOConverter fileDTOConverter;
    @Override
    public ResponseEntity<ResponseDTO<FileDTO>> insert(RequestDTO<FileDTO> request) {

        HttpHeaders headers = new HttpHeaders();
        ResponseDTO<FileDTO> response;

        File file=fileService.insert(fileDTOConverter.convertToEntity(request.getRequest()));

        if(file==null) {
            throw new HttpInsertFailedException("Error to save");
        }

        return ResponseEntity.ok().build();
    }
}
