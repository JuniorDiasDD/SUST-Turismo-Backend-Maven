package susturismo.susturismo.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import susturismo.susturismo.domain.CommentFeed;
import susturismo.susturismo.exeption.exeptions.HttpUpdateFailedException;
import susturismo.susturismo.service.CommentFeedService;
import susturismo.susturismo.web.dto.CommentFeedDTO;
import susturismo.susturismo.web.dto.webBody.requests.RequestDTO;

import java.util.UUID;

@RestController
public class CommentFeedController implements CommentFeedApi{

    @Autowired
    CommentFeedService commentFeedService;


    @Override
    public ResponseEntity<Object> insert(RequestDTO<CommentFeedDTO> request) {

        CommentFeed value =commentFeedService.insert(request.getRequest().getFeed_id(),request.getRequest().getComment());

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> delete(UUID id,RequestDTO<CommentFeedDTO> request) {

       boolean check= commentFeedService.delete(id);
if(check){
    throw  new HttpUpdateFailedException("Error in delete comment for Feed");
}
        return ResponseEntity.ok().build();
    }
}
