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
        eventDTO.setDate_finish(event.getDate_finish());
        eventDTO.setDate_init(event.getDate_init());
        eventDTO.setHour_finish(event.getHour_finish());
        eventDTO.setImage(event.getImage());
        eventDTO.setHour_init(event.getHour_init());
        eventDTO.setOrganizer(event.getOrganizer());
        eventDTO.setTags(event.getTags());
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
        event.setDate_finish(eventDTO.getDate_finish());
        event.setDate_init(eventDTO.getDate_init());
        event.setHour_finish(eventDTO.getHour_finish());
        event.setImage(eventDTO.getImage());
        event.setHour_init(eventDTO.getHour_init());
        event.setOrganizer(eventDTO.getOrganizer());
        event.setTags(eventDTO.getTags());
        event.setLocal(eventDTO.getLocal());
        event.setTitle(eventDTO.getTitle());
        if(eventDTO.getCategories()!=null &&!eventDTO.getCategories().isEmpty()){
            Set<Category> categories = eventDTO.getCategories().stream().map(categoryDTOConverter::convertToEntity).collect(Collectors.toSet());
            event.setCategory(categories);
        }

        return event;

    }


}
