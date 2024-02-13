package susturismo.susturismo.web.dto.converter;

import susturismo.susturismo.domain.Category;
import susturismo.susturismo.domain.Event;
import susturismo.susturismo.domain.User;
import susturismo.susturismo.web.dto.AccountDTO;
import susturismo.susturismo.web.dto.CategoryDTO;
import susturismo.susturismo.web.dto.EventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import susturismo.susturismo.web.dto.NoticiaDTO;

import java.util.HashSet;
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
        eventDTO.setPrice(event.getPrice());
        if(event.getCategory()!=null && !event.getCategory().isEmpty()) {
            Set<CategoryDTO> categoryDTOS = event.getCategory().stream().map(categoryDTOConverter::convertToDTO).collect(Collectors.toSet());
            eventDTO.setCategories(categoryDTOS);
        }
        AccountDTO accountDTO=accountDTOConverter.convertToDTO(event.getAccount());
        eventDTO.setAccount(accountDTO);
        if(!event.getSemelhantes().isEmpty()){
            Set<EventDTO> eventDTOS = new HashSet<>();
            event.getSemelhantes().forEach(v->{
                eventDTOS.add(convertToDTOExtra(v));
            });
            eventDTO.setSemelhantes(eventDTOS);

        }
        if(!event.getGalery().isEmpty()){
            eventDTO.setGalery(event.getGalery());
        }
        return eventDTO;

    }
    public EventDTO convertToDTOExtra(Event event) {

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
        eventDTO.setPrice(event.getPrice());
        if (event.getCategory()!=null && !event.getCategory().isEmpty()) {


        Set<CategoryDTO> categoryDTOS = event.getCategory().stream().map(categoryDTOConverter::convertToDTO).collect(Collectors.toSet());
        eventDTO.setCategories(categoryDTOS);
    }
        if(!event.getGalery().isEmpty()){
            eventDTO.setGalery(eventDTO.getGalery());
        }
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
        event.setPrice(eventDTO.getPrice());
        if(eventDTO.getCategories()!=null &&!eventDTO.getCategories().isEmpty()){
            Set<Category> categories = eventDTO.getCategories().stream().map(categoryDTOConverter::convertToEntity).collect(Collectors.toSet());
            event.setCategory(categories);
        }

        if(eventDTO.getGalery()!=null && !eventDTO.getGalery().isEmpty()){
            event.setGalery(eventDTO.getGalery());
        }

        return event;

    }


}
