package susturismo.susturismo.web.dto.converter;

import susturismo.susturismo.domain.Category;
import susturismo.susturismo.domain.Event;
import susturismo.susturismo.web.dto.CategoryDTO;
import susturismo.susturismo.web.dto.EventDTO;
import susturismo.susturismo.web.dto.TokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CategoryDTOConverter {


    public CategoryDTO convertToDTO(Category category) {

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());
        categoryDTO.setStatus(category.getStatus());

        //Set<EventDTO> eventsDTOList = category.getEvents().stream().map(eventDTOConverter::convertToDTO).collect(Collectors.toSet());
       // categoryDTO.setEvents(eventsDTOList);

        return categoryDTO;
    }

    public Category convertToEntity(CategoryDTO categoryDTO) {

        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setStatus(categoryDTO.getStatus());

     /*   if(categoryDTO.getEvents()!=null || !category.getEvents().isEmpty()){
            Set<Event> eventsList = categoryDTO.getEvents().stream().map(eventDTOConverter::convertToEntity).collect(Collectors.toSet());
            category.setEvents(eventsList);
        }*/


        return category;
    }


}
