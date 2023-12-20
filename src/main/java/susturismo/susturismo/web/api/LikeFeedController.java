package susturismo.susturismo.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import susturismo.susturismo.domain.LikeFeed;
import susturismo.susturismo.exeption.exeptions.HttpUpdateFailedException;
import susturismo.susturismo.service.LikeFeedService;
import susturismo.susturismo.web.dto.LikeDTO;
import susturismo.susturismo.web.dto.converter.responsesConverters.ResponseDTOConverter;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTO;

@RestController
public class LikeFeedController implements LikeFeedApi{

    @Autowired
    ResponseDTOConverter responseDTOConverter;
    @Autowired
    LikeFeedService likeFeedService;


    @Override
    public ResponseEntity<Object> insert(RequestDTO<LikeDTO> request) {

        LikeFeed likeFeed =likeFeedService.insert(request.getRequest().getFeed_id());

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> delete(RequestDTO<LikeDTO> request) {

       boolean check= likeFeedService.delete(request.getRequest().getFeed_id());
if(!check){
    throw  new HttpUpdateFailedException("Error in delete like Feed");
}
        return ResponseEntity.ok().build();
    }
}
