package susturismo.susturismo.web.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import susturismo.susturismo.domain.Category;
import susturismo.susturismo.domain.Event;
import susturismo.susturismo.domain.Noticia;
import susturismo.susturismo.web.dto.AccountDTO;
import susturismo.susturismo.web.dto.CategoryDTO;
import susturismo.susturismo.web.dto.EventDTO;
import susturismo.susturismo.web.dto.NoticiaDTO;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class NoticiaDTOConverter {

    @Autowired
    CategoryDTOConverter categoryDTOConverter;
    @Autowired
    AccountDTOConverter accountDTOConverter;

    public NoticiaDTO convertToDTO(Noticia noticia) {

        NoticiaDTO dto = new NoticiaDTO();

        dto.setId(noticia.getId());
        dto.setDescription(noticia.getDescription());
        dto.setStatus(noticia.getStatus());
        dto.setDate_publicacao(noticia.getDate_publicacao());
        dto.setFonte(noticia.getFonte());
        dto.setTitle(noticia.getTitle());
        dto.setTags(noticia.getTags());
        dto.setImage(noticia.getImage());
        if(noticia.getCategory()!=null && !noticia.getCategory().isEmpty()){
            Set<CategoryDTO> categoryDTOS = noticia.getCategory().stream().map(categoryDTOConverter::convertToDTO).collect(Collectors.toSet());
            dto.setCategories(categoryDTOS);
        }



    if(!noticia.getSemelhantes().isEmpty()){
        Set<NoticiaDTO> noticiaDTOS = new HashSet<>();
                noticia.getSemelhantes().forEach(v->{
                    noticiaDTOS.add(convertToDTOExtra(v));
                });
        dto.setSemelhantes(noticiaDTOS);

    }
        AccountDTO accountDTO=accountDTOConverter.convertToDTO(noticia.getAccount());
        dto.setAccount(accountDTO);
        if(!noticia.getGalery().isEmpty()){
            dto.setGalery(noticia.getGalery());
        }
        return dto;

    }

    public NoticiaDTO convertToDTOExtra(Noticia noticia) {

        NoticiaDTO dto = new NoticiaDTO();

        dto.setId(noticia.getId());
        dto.setDescription(noticia.getDescription());
        dto.setStatus(noticia.getStatus());
        dto.setDate_publicacao(noticia.getDate_publicacao());
        dto.setFonte(noticia.getFonte());
        dto.setTitle(noticia.getTitle());
        dto.setTags(noticia.getTags());
        dto.setImage(noticia.getImage());
        if(noticia.getCategory()!=null && !noticia.getCategory().isEmpty()){
            Set<CategoryDTO> categoryDTOS = noticia.getCategory().stream().map(categoryDTOConverter::convertToDTO).collect(Collectors.toSet());
            dto.setCategories(categoryDTOS);
        }
        if(noticia.getGalery()!=null &&!noticia.getGalery().isEmpty()){
            dto.setGalery(noticia.getGalery());
        }
        return dto;

    }
    public Noticia convertToEntity(NoticiaDTO dto) {

        Noticia noticia = new Noticia();
        noticia.setId(dto.getId());
        noticia.setDescription(dto.getDescription());
        noticia.setStatus(dto.getStatus());
        noticia.setDate_publicacao(dto.getDate_publicacao());
        noticia.setFonte(dto.getFonte());
        noticia.setTitle(dto.getTitle());
        noticia.setTags(dto.getTags());
        noticia.setImage(dto.getImage());
        if(dto.getCategories()!=null && !dto.getCategories().isEmpty()){
            Set<Category> categories = dto.getCategories().stream().map(categoryDTOConverter::convertToEntity).collect(Collectors.toSet());
            noticia.setCategory(categories);
        }

        if(dto.getGalery()!=null && !dto.getGalery().isEmpty()){
            noticia.setGalery(dto.getGalery());
        }
        return noticia;

    }


}
