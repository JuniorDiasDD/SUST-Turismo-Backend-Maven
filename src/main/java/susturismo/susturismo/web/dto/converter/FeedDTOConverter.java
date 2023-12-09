package susturismo.susturismo.web.dto.converter;

import susturismo.susturismo.domain.*;
import susturismo.susturismo.web.dto.AccountDTO;
import susturismo.susturismo.web.dto.CategoryDTO;
import susturismo.susturismo.web.dto.FeedDTO;
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

    public FeedDTO convertToDTO(Feed feed) {

        FeedDTO dto = new FeedDTO();

        dto.setId(feed.getId());
        dto.setDescription(feed.getDescription());
        dto.setStatus(feed.getStatus());
        dto.setImage(feed.getImage());
        dto.setData(feed.getCriadoEm());

        AccountDTO accountDTO=accountDTOConverter.convertToDTO(feed.getAccount());
        dto.setAccount(accountDTO);
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
