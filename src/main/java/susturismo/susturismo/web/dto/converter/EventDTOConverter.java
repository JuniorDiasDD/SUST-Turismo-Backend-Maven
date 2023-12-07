package susturismo.susturismo.web.dto.converter;

import susturismo.susturismo.domain.Category;
import susturismo.susturismo.domain.Event;
import susturismo.susturismo.domain.User;
import susturismo.susturismo.web.dto.AccountDTO;
import susturismo.susturismo.web.dto.CategoryDTO;
import susturismo.susturismo.web.dto.EventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EventDTOConverter {

    @Autowired
    CategoryDTOConverter categoryDTOConverter;
    @Autowired
    AccountDTOConverter accountDTOConverter;

    public EventDTO convertToDTO(Event event) {

        EventDTO eventDTO = new EventDTO();

        eventDTO.setId(event.getId());
    eventDTO.setDescription(event.getDescription());
    eventDTO.setStatus(event.getStatus());
    eventDTO.setDate(event.getDate());
    eventDTO.setHour(event.getHour());
    eventDTO.setLocal(event.getLocal());
    eventDTO.setTitle(event.getTitle());

        Set<CategoryDTO> categoryDTOS = event.getCategory().stream().map(categoryDTOConverter::convertToDTO).collect(Collectors.toSet());
    eventDTO.setCategories(categoryDTOS);

        AccountDTO accountDTO=accountDTOConverter.convertToDTO(event.getAccount());
        eventDTO.setAccount(accountDTO);

        return eventDTO;

    }

    public Event convertToEntity(EventDTO eventDTO) {

        Event event = new Event();
        event.setId(eventDTO.getId());
        event.setDescription(eventDTO.getDescription());
        event.setStatus(eventDTO.getStatus());
        event.setHour(eventDTO.getHour());
        event.setLocal(eventDTO.getLocal());
        event.setTitle(eventDTO.getTitle());
        if(eventDTO.getCategories()!=null &&!eventDTO.getCategories().isEmpty()){
            Set<Category> categories = eventDTO.getCategories().stream().map(categoryDTOConverter::convertToEntity).collect(Collectors.toSet());
            event.setCategory(categories);
        }

        event.setDate(eventDTO.getDate());
        return event;

    }


}
