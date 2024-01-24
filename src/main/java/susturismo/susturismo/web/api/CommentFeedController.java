package susturismo.susturismo.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import susturismo.susturismo.domain.CommentFeed;
import susturismo.susturismo.exeption.exeptions.HttpUpdateFailedException;
import susturismo.susturismo.service.CommentFeedService;
import susturismo.susturismo.web.dto.CommentFeedDTO;
import susturismo.susturismo.web.dto.converter.responsesConverters.ResponseDTOConverter;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTO;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTO;
import java.util.UUID;

@RestController
public class CommentFeedController implements CommentFeedApi{

    @Autowired
    CommentFeedService commentFeedService;

    @Autowired
    ResponseDTOConverter responseDTOConverter;
    @Override
    public ResponseEntity<Object> insert(RequestDTO<CommentFeedDTO> request) {
        ResponseDTO<CommentFeedDTO> response;
        HttpHeaders headers = new HttpHeaders();
        CommentFeed value =commentFeedService.insert(request.getRequest().getFeed_id(),request.getRequest().getComment());

        response = responseDTOConverter.createResponse(request, null, "Success", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> delete(UUID id,RequestDTO<CommentFeedDTO> request) {
        ResponseDTO<CommentFeedDTO> response;
        HttpHeaders headers = new HttpHeaders();
       boolean check= commentFeedService.delete(id);
if(check){
    throw  new HttpUpdateFailedException("Error in delete comment for Feed");
}
        response = responseDTOConverter.createResponse(request, null, "Success", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
