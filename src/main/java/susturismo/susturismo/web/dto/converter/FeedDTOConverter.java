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
        dto.setTitle(feed.getTitle());
        if(!feed.getCategory().isEmpty()) {
            Set<CategoryDTO> categoryDTOS = feed.getCategory().stream().map(categoryDTOConverter::convertToDTO).collect(Collectors.toSet());
            dto.setCategories(categoryDTOS);
        }
        AccountDTO accountDTO=accountDTOConverter.convertToDTO(feed.getAccount());
        dto.setAccount(accountDTO);
        return dto;

    }

    public Feed convertToEntity(FeedDTO dto) {

        Feed objt = new Feed();
        objt.setId(dto.getId());
        objt.setDescription(dto.getDescription());
        objt.setStatus(dto.getStatus());
        objt.setTitle(dto.getTitle());

        if(dto.getCategories()!=null && !dto.getCategories().isEmpty()){
            Set<Category> categories = dto.getCategories().stream().map(categoryDTOConverter::convertToEntity).collect(Collectors.toSet());
            objt.setCategory(categories);
        }

        return objt;

    }


}
