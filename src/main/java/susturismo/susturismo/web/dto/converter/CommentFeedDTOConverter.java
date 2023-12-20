package susturismo.susturismo.web.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import susturismo.susturismo.domain.CommentFeed;
import susturismo.susturismo.web.dto.AccountDTO;
import susturismo.susturismo.web.dto.CommentFeedDTO;

@Component
public class CommentFeedDTOConverter {
    @Autowired
    AccountDTOConverter accountDTOConverter;

    public CommentFeedDTO convertToDTO(CommentFeed commentFeed) {

        CommentFeedDTO dto = new CommentFeedDTO();
        dto.setId(commentFeed.getId());
        dto.setFeed_id(commentFeed.getFeed_id());
        dto.setComment(commentFeed.getComment());
dto.setData(commentFeed.getCriadoEm());
if(commentFeed.getAccount()!=null) {
    AccountDTO accountDTO = accountDTOConverter.convertToDTO(commentFeed.getAccount());
    dto.setAccount(accountDTO);
}
        return dto;
    }
}
