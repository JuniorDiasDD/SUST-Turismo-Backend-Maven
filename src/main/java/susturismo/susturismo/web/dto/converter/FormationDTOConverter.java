package susturismo.susturismo.web.dto.converter;

import susturismo.susturismo.domain.Category;
import susturismo.susturismo.domain.Formation;
import susturismo.susturismo.web.dto.CategoryDTO;
import susturismo.susturismo.web.dto.FormationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FormationDTOConverter {

    @Autowired
    CategoryDTOConverter categoryDTOConverter;

    public FormationDTO convertToDTO(Formation formation) {

        FormationDTO dto = new FormationDTO();

        dto.setId(formation.getId());
        dto.setDescription(formation.getDescription());
        dto.setStatus(formation.getStatus());
        dto.setTitle(formation.getTitle());
        dto.setObjective(formation.getObjective());
        dto.setResponsible(formation.getResponsible());
        if(!formation.getCategory().isEmpty()) {
            Set<CategoryDTO> categoryDTOS = formation.getCategory().stream().map(categoryDTOConverter::convertToDTO).collect(Collectors.toSet());
            dto.setCategories(categoryDTOS);
        }

        return dto;

    }

    public Formation convertToEntity(FormationDTO dto) {

        Formation objt = new Formation();
        objt.setId(dto.getId());
        objt.setDescription(dto.getDescription());
        objt.setStatus(dto.getStatus());
        objt.setTitle(dto.getTitle());
        objt.setObjective(dto.getObjective());
        objt.setResponsible(dto.getResponsible());

        if(dto.getCategories()!=null && !dto.getCategories().isEmpty()){
            Set<Category> categories = dto.getCategories().stream().map(categoryDTOConverter::convertToEntity).collect(Collectors.toSet());
            objt.setCategory(categories);
        }

        return objt;

    }


}
