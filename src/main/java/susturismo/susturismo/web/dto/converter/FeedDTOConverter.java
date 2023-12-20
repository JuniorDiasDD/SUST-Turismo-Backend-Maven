package susturismo.susturismo.web.dto.converter;

import susturismo.susturismo.domain.*;
import susturismo.susturismo.web.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FeedDTOConverter {

    @Autowired
    CategoryDTOConverter categoryDTOConverter;
    @Autowired
    AccountDTOConverter accountDTOConverter;
    @Autowired
    CommentFeedDTOConverter commentFeedDTOConverter;

    public FeedDTO convertToDTO(Feed feed) {

        FeedDTO dto = new FeedDTO();

        dto.setId(feed.getId());
        dto.setDescription(feed.getDescription());
        dto.setStatus(feed.getStatus());
        dto.setImage(feed.getImage());
        dto.setData(feed.getCriadoEm());
        dto.setLikes(feed.getCount_likes());
        dto.setUser_like(feed.isUser_like());

        AccountDTO accountDTO=accountDTOConverter.convertToDTO(feed.getAccount());
        dto.setAccount(accountDTO);

        Set<CommentFeedDTO> commentFeedDTOS = feed.getComments().stream().map(commentFeedDTOConverter::convertToDTO).collect(Collectors.toSet());
        dto.setComments(commentFeedDTOS);
        return dto;

    }

    public Feed convertToEntity(FeedDTO dto) {

        Feed objt = new Feed();
        objt.setId(dto.getId());
        objt.setDescription(dto.getDescription());
        objt.setStatus(dto.getStatus());
        objt.setImage(dto.getImage());

        return objt;

    }


}
