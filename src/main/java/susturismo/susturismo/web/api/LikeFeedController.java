package susturismo.susturismo.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import susturismo.susturismo.domain.LikeFeed;
import susturismo.susturismo.exeption.exeptions.HttpUpdateFailedException;
import susturismo.susturismo.service.LikeFeedService;
import susturismo.susturismo.web.dto.FormationDTO;
import susturismo.susturismo.web.dto.LikeDTO;
import susturismo.susturismo.web.dto.converter.responsesConverters.ResponseDTOConverter;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTO;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTO;
import susturismo.susturismo.web.dto.webBody.responses.ResponseDTOList;

@RestController
public class LikeFeedController implements LikeFeedApi{

    @Autowired
    ResponseDTOConverter responseDTOConverter;
    @Autowired
    LikeFeedService likeFeedService;


    @Override
    public ResponseEntity<Object> insert(RequestDTO<LikeDTO> request) {

        ResponseDTO<LikeDTO> response;
        HttpHeaders headers = new HttpHeaders();
        LikeFeed likeFeed =likeFeedService.insert(request.getRequest().getFeed_id());
        response = responseDTOConverter.createResponse(request, null, "Success", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> delete(RequestDTO<LikeDTO> request) {

        ResponseDTO<LikeDTO> response;
        HttpHeaders headers = new HttpHeaders();
       boolean check= likeFeedService.delete(request.getRequest().getFeed_id());
    if(!check){
        throw  new HttpUpdateFailedException("Error in delete like Feed");
    }
        response = responseDTOConverter.createResponse(request, null, "Success", true);


        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
